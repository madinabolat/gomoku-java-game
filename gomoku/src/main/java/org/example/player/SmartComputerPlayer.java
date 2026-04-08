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
        super(cellState, generateRandomName(), PlayerType.SMART_COMPUTER);
        this.winChecker = winChecker;
    }

    @Override
    public int[] generateCoordinates(Board board){
        System.out.println("Computer " + name + " choosing its move:");
        int x = -1;
        int y = -1;
        CellState[][] boardArray = board.board;

        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {



                if (boardArray[i][j] != CellState.EMPTY && boardArray[i][j] != cellState) {
                    int max = winChecker.maxConsecutiveLengthWithDirections(i, j)[0];
                    int max_dx = winChecker.maxConsecutiveLengthWithDirections(i, j)[1];
                    int max_dy = winChecker.maxConsecutiveLengthWithDirections(i, j)[2];

                    if (max >= winChecker.numberOfConsecutiveCellsToWin-1) {
                        x = i + (max-1) * max_dx;
                        y = j + max_dy;
                        break;
                    }
                }

                if (boardArray[i][j] == cellState) {
                    int max = winChecker.maxConsecutiveLengthWithDirections(i, j)[0];
                    int max_dx = winChecker.maxConsecutiveLengthWithDirections(i, j)[1];
                    int max_dy = winChecker.maxConsecutiveLengthWithDirections(i, j)[2];
                        x = i + (max-1) * max_dx;
                        y = j + (max-1) * max_dy;
                        break;
                }

                if (boardArray[i][j] == CellState.EMPTY) {
                    Random r = new Random();
                    x = r.nextInt(board.boardSize-1);
                    y = r.nextInt(board.boardSize-1);
                    break;
                }



            }
        }
        return new int[] {x,y};
    }

}


