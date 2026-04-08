package org.example.player;
import org.example.board.Board;
import org.example.board.CellState;
import java.util.Scanner;
import java.util.UUID;

public abstract class Player {
    public final CellState cellState;
    public final String name;
    public final PlayerType playerType;

    public Player(CellState cellState, String name, PlayerType playerType) {
        this.cellState = cellState;
        this.name = name;
        this.playerType = playerType;
    }

    public static String generateRandomName(){
        String name = UUID.randomUUID().toString().substring(0,5);
        return name;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Player name: ").append(name).append(", Player type: ").append(playerType).append(", Cell type: ").append(cellState.getSymbol()).toString();
    }


    protected abstract int[] generateCoordinates(Board board);

    public Move getMove(Board board) {
        int[] coordinates = generateCoordinates(board);
        int x = coordinates[0];
        int y = coordinates[1];
        return new Move(x, y, cellState);
    }

}




