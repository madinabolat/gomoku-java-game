package org.example.player;

import org.example.board.Board;
import org.example.board.CellState;

import java.util.Scanner;

public class HumanPlayer {
    public Scanner scanner;
    public CellState cellState;
    public String name;

    public HumanPlayer(Scanner scanner, CellState cellState, String name){
        this.scanner = scanner;
        this.cellState = cellState;
        this.name = name;
    }


    public Move getMove() {

        System.out.println(name + ", enter your move in 'row,column' format (example: '3,3' if you want to place your move in 3rd row, 3rd column");



        String input;
        String[] coordinates;
        int x = -1;
        int y = -1;

        while (true) {
            input = scanner.nextLine();
            coordinates = input.split(",");

            //add what if more commas or no comma

            try {
                x = Integer.parseInt(coordinates[0])-1;
                y = Integer.parseInt(coordinates[1])-1;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer"); //change this language what if 1,a or a,1 or a,a
            }
        }

        return new Move(x,y,cellState);

    }

}




