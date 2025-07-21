package org.example.player;
import org.example.board.CellState;
import java.util.Scanner;

public class HumanPlayer {
    public Scanner scanner;
    public CellState cellState;
    public String name;

    public HumanPlayer(Scanner scanner, CellState cellState, String name) {
        this.scanner = scanner;
        this.cellState = cellState;
        this.name = name;
    }

    public Move getMove() {
        System.out.println(name + ", enter your move in 'row,column' format " +
                "(example: '3,3' if you want to place your move in 3rd row, 3rd column). Row, column numbers start from 1.");

        String input;
        String[] coordinates;
        int x = -1;
        int y = -1;

        while (true) {
            input = scanner.nextLine();
            coordinates = input.split(",");

            if (coordinates.length < 2) {
                System.out.println("You entered too few arguments. Enter exactly two integers separated by comma.");
                continue;
            } else if (coordinates.length > 2) {
                System.out.println("You entered too many arguments. Enter exactly two integers separated by comma.");
                continue;
            }

            try {
                x = Integer.parseInt(coordinates[0].trim()) - 1;
            }
            catch (NumberFormatException e) {
                System.out.println("Input " + coordinates[0] + " is not valid. Enter a valid integer");
                continue;
            }

            try {
                y = Integer.parseInt(coordinates[1].trim()) - 1;
            }
            catch (NumberFormatException e) {
                System.out.println("Input " + coordinates[1] + " is not valid. Enter a valid integer");
                continue;
            }
            break;
        }

        return new Move(x, y, cellState);

    }
}




