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
            for (int j = 0; j<board.boardSize; j++){
                //defense - dont let the opponent win

                HashMap<Integer, int[]> directionLengthPair = new HashMap<>();

                System.out.println("i,j are "+i+","+j);

                if (boardArray[i][j] != cellState && boardArray[i][j] != CellState.EMPTY) {
                    for (int[] dir : winChecker.directions) {
                        System.out.println("Checking dir"+dir[0]+","+dir[1]);
                        int dx = dir[0];
                        int dy = dir[1];
                        int lengthConsecutiveCells = winChecker.countLengthConsecutiveCells(i, j, dx, dy);
                        System.out.println("Length is"+lengthConsecutiveCells);
                        directionLengthPair.put(lengthConsecutiveCells, dir);
                    }
                    int maxLength = Collections.max(directionLengthPair.keySet());
                    System.out.println("Max length is "+maxLength);
                    int[] maxLengthDir = directionLengthPair.get(maxLength); //get value by key
                    System.out.println("Dir for max length are "+ maxLengthDir[0]+","+maxLengthDir[1]);

                    x = i - maxLength * maxLengthDir[0];
                    y = j - maxLength * maxLengthDir[1];
                    System.out.println("chosen coords "+ x +"," + y);
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
