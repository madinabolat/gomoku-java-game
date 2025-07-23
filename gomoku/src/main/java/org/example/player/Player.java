package org.example.player;
import org.example.board.CellState;
import java.util.Scanner;

public abstract class Player {
    public final CellState cellState;
    public final String name;

    public Player(CellState cellState, String name) {
        this.cellState = cellState;
        this.name = name;
    }

    public abstract int[] generateCoordinates();

    public Move getMove() {
        int x = generateCoordinates()[0];
        int y = generateCoordinates()[1];
        return new Move(x, y, cellState);
    }


}




