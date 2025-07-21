package org.example.player;
import org.example.board.CellState;

public class Move {
    public int x;
    public int y;
    public CellState cellState;

    public Move(int x, int y, CellState cellState){
        this.x = x;
        this.y = y;
        this.cellState = cellState;
    }
}
