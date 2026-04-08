package org.example.player;

import org.example.board.Board;
import org.example.board.CellState;
import java.util.Random;

public class DumbComputerPlayer extends Player{

    public DumbComputerPlayer(CellState cellState) {
        super(cellState, generateRandomName(), PlayerType.DUMB_COMPUTER);
    }

    @Override
    protected int[] generateCoordinates(Board board){
        Random r = new Random();
        int x = r.nextInt(board.boardSize);
        int y = r.nextInt(board.boardSize);
        return new int[] {x,y};
    }
}
