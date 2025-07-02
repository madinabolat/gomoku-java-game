package org.example.player;
import org.example.board.CellState;

public class Player {
    public String name;
    public int[] currentMove;
    public CellState moveType;

    Player(){
        this.currentMove = new int[2];
    }
}
