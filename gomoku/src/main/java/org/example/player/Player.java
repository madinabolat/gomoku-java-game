package org.example.player;
import org.example.board.CellState;
import java.util.Scanner;

public abstract class Player {
    public final CellState cellState;
    public final String name;
    public final PlayerType playerType;

    public Player(CellState cellState, String name, PlayerType playerType) {
        this.cellState = cellState;
        this.name = name;
        this.playerType = playerType;
    }

    public abstract int[] generateCoordinates();

    @Override
    public String toString() {
        return new StringBuilder().append("Player name: ").append(name).append(", Player type: ").append(playerType).append(", Cell type: ").append(cellState).toString();
    }

    public Move getMove() {
        int x = generateCoordinates()[0];
        int y = generateCoordinates()[1];
        return new Move(x, y, cellState);
    }


}




