package org.example.player;
import org.example.board.Board;
import org.example.board.CellState;

import java.util.Random;
import java.util.Scanner;

public class HumanPlayer extends Player{
    public Scanner scanner;

    public HumanPlayer(Scanner scanner, CellState cellState, String name) {
        super(cellState, name, PlayerType.HUMAN);
        this.scanner = scanner;
    }

    @Override
    public int[] generateCoordinates(Board board){
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

        return new int[] {x,y};
    }

}




