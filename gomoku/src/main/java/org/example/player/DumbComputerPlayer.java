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
        int x = r.nextInt(board.boardSize-1);
        int y = r.nextInt(board.boardSize-1);
        if (!board.checkIfCellEmpty(x,y)){
            while(!board.checkIfCellEmpty(x,y)){
                x = r.nextInt(board.boardSize-1);
                y = r.nextInt(board.boardSize-1);
            }
        }
        return new int[] {x,y};
    }
}
