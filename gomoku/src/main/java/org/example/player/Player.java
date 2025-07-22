package org.example.player;
import org.example.board.CellState;
import java.util.Scanner;

public abstract class Player {
    public Scanner scanner;
    public CellState cellState;
    public String name;

    public Player(Scanner scanner, CellState cellState) {
        this.scanner = scanner;
        this.cellState = cellState;
    }


}




