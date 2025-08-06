package org.example.player;

import org.example.board.Board;
import org.example.board.CellState;
import java.util.Random;
import java.util.UUID;


public class DumbComputerPlayer extends Player{

    public DumbComputerPlayer(CellState cellState) {
        super(cellState, generateRandomName(), PlayerType.DUMB_COMPUTER);
        //think about making the name as a random string
    }

    public static String generateRandomName(){
        String name = UUID.randomUUID().toString().substring(0,5);
        return name;
    }

    @Override
    public int[] generateCoordinates(Board board){
        System.out.println("Computer " + name + " choosing its move:");
        Random r = new Random();
        int x = r.nextInt(board.boardSize-1);
        int y = r.nextInt(board.boardSize-1);
        return new int[] {x,y};
    }

}
