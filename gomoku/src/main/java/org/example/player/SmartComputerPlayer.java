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

        //add max length direction to WinChecker
        
        for (int i = 0; i < board.boardSize; i++){
            for (int j = 0; j < board.boardSize; j++){
                //scenario 1
                if (boardArray[i][j]!=CellState.EMPTY && boardArray[i][j]!=cellState){
                    //find max length direction
                    for (int[] dir : winChecker.directions) {
                        int dx = dir[0];
                        int dy = dir[1];
                        if (winChecker.countConsecutive(i,j,dx,dy)> winChecker.numberOfConsecutiveCellsToWin-1){
                            x = i + dx* winChecker.countConsecutive(i,j,dx,dy);
                            y = j + dy* winChecker.countConsecutive(i,j,dx,dy);
                            break;
                        }
                    }
                }
                //scenario 2
                if (boardArray[i][j]==cellState){
                    for (int[] dir : winChecker.directions) {
                        int dx = dir[0];
                        int dy = dir[1];
                        //find max length direction
                        if (winChecker.countConsecutive(i,j,dx,dy)> winChecker.numberOfConsecutiveCellsToWin-1){
                            x = i + dx* winChecker.countConsecutive(i,j,dx,dy);
                            y = j + dy* winChecker.countConsecutive(i,j,dx,dy);
                            break;
                        }
                    }
                }
                //scenario 3
                //else just pick a cell

            }
        }


        return new int[] {x,y};
    }

}
