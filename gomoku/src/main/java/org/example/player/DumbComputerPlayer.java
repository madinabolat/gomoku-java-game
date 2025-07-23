package org.example.player;

import org.example.board.CellState;
import java.util.Random;


public class DumbComputerPlayer extends Player{

    public DumbComputerPlayer(CellState cellState) {
        super(cellState, "Computer");
    }

    public int[] generateCoordinates(){
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();
        return new int[] {x,y};
    }


}
