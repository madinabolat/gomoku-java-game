package org.example.player;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.game.WinChecker;

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
                if (winChecker.isWinningMove(i,j)){ //win yourself (if the cell state is yours) or prevent the other winning (if the cell state is the opposite)
                    //but this wont work: isWinning calls a method which checks if the starting cell is not empty. so rethink. 
                    x = i;
                    y = j;
                    break;
                }
                while (true) {
                    if (!board.checkIfValidCoordinates(x, y)) {
                        continue;
                    }
                    if (!board.checkIfCellEmpty(x, y)) {
                        continue;
                    }
                    break;
                }
            }
        }

        return new int[] {x,y};
    }

}
