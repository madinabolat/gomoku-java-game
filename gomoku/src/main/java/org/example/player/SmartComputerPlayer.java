package org.example.player;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.game.WinChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SmartComputerPlayer extends Player{
    WinChecker winChecker;

    public SmartComputerPlayer(CellState cellState, WinChecker winChecker) {
        super(cellState, generateRandomName(), PlayerType.SMART_COMPUTER);
        this.winChecker = winChecker;
    }

    public int[] getCellWithHighestConsecutiveLength(Board board, CellState cellState){
        CellState[][] boardArray = board.board;
        ArrayList<int[]> occupiedCellsOfOneType = new ArrayList<>();
        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {
                if (boardArray[i][j] == cellState){
                    occupiedCellsOfOneType.add(new int[]{i,j});
                }
            }
        }
        int maxConsecutiveLength = 0;
        int[] cellCoordinatesWithMaxConsecutiveLength = new int[2];
        for (int[] coordinates : occupiedCellsOfOneType){
            int maxConsecutiveLengthForCell = winChecker.maxConsecutiveLengthWithDirections(coordinates[0], coordinates[1])[0];
            if (maxConsecutiveLength < maxConsecutiveLengthForCell){
                maxConsecutiveLength = maxConsecutiveLengthForCell;
                cellCoordinatesWithMaxConsecutiveLength = coordinates;
            }
        }
        return cellCoordinatesWithMaxConsecutiveLength;
    }

    public int[] cellDecision(Board board){
        CellState opponentCellState = null;
        for (CellState cellType : CellState.values()){
            if (cellType != cellState && cellType != CellState.EMPTY){
                opponentCellState = cellType;
            }
        }
        int[] opponentCellWithMaxLength = getCellWithHighestConsecutiveLength(board, opponentCellState);
        int[] myCellWithMaxLength = getCellWithHighestConsecutiveLength(board, cellState);
        int opponentMaxLength = winChecker.maxConsecutiveLengthWithDirections(opponentCellWithMaxLength[0], opponentCellWithMaxLength[1])[0];
        int myMaxLength = winChecker.maxConsecutiveLengthWithDirections(myCellWithMaxLength[0], myCellWithMaxLength[1])[0];
        if (opponentMaxLength >= myMaxLength){
            return opponentCellWithMaxLength;
        } else {
            return myCellWithMaxLength;
        }
        //TO DO: improve logic. misses threats.
    }

    @Override
    public int[] generateCoordinates(Board board){
        int i = cellDecision(board)[0];
        int j = cellDecision(board)[1];
        int max = winChecker.maxConsecutiveLengthWithDirections(i, j)[0];
        int max_dx = winChecker.maxConsecutiveLengthWithDirections(i, j)[1];
        int max_dy = winChecker.maxConsecutiveLengthWithDirections(i, j)[2];
        int x = i + max * max_dx;
        int y = j + max * max_dy;
        if (!board.checkIfCellEmpty(x,y)){
            Random r = new Random();
            while(!board.checkIfCellEmpty(x,y)){
                x = r.nextInt(board.boardSize-1);
                y = r.nextInt(board.boardSize-1);
            }
        }
        return new int[] {x,y};
    }

}


