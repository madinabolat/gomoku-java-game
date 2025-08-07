package org.example.player;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.game.WinChecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;


public class SmartComputerPlayer extends Player{
    WinChecker winChecker;

    public SmartComputerPlayer(CellState cellState, WinChecker winChecker) {
        super(cellState, generateRandomName(), PlayerType.DUMB_COMPUTER);
        this.winChecker = winChecker;
    }

    public static String generateRandomName(){
        String name = UUID.randomUUID().toString().substring(0,5);
        return name;
    }

    @Override
    public int[] generateCoordinates(Board board){
        System.out.println("Computer " + name + " choosing its move:");
        int x = -1;
        int y = -1;
        CellState[][] boardArray = board.board;


        for (int i = 0; i<board.boardSize; i++){
            for (int j = 0; i<board.boardSize; i++){
                //defense - dont let the opponent win

                HashMap<Integer, int[]> directionLengthPair = new HashMap<>();

                if (boardArray[i][j] != cellState && boardArray[i][j] != CellState.EMPTY) {
                    for (int[] dir : winChecker.directions) {
                        int dx = dir[0];
                        int dy = dir[1];
                        int lengthConsecutiveCells = winChecker.countLengthConsecutiveCells(i, j, dx, dy);
                        directionLengthPair.put(lengthConsecutiveCells, dir);
                    }
                    int maxLength = Collections.max(directionLengthPair.keySet());
                    int[] maxLengthDir = directionLengthPair.get(maxLength); //get value by key

                    x = i + (maxLength-1) * maxLengthDir[0];
                    y = j + (maxLength-1) * maxLengthDir[1];
                }


                //offense - find best route to win for you
//


                //giving infinite loop
//                while (true) {
//                    if (!board.checkIfValidCoordinates(x, y)) {
//                        continue;
//                    }
//                    if (!board.checkIfCellEmpty(x, y)) {
//                        continue;
//                    }
//                    break;
//                }
            }
        }

        return new int[] {x,y};
    }

}
