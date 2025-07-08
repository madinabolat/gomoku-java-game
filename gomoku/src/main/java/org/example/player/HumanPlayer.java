package org.example.player;

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

        scanner.useDelimiter(",");
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        return new Move(x,y,cellState);

//        while (true){
//            try {
//                scanner.useDelimiter(",");
//
//                //case 1 if not proper delimiter
//                //case 2 if proper delimiter ->
//                 // case2.1 if not integer in either position
//
//                int x = scanner.nextInt() - 1;
//                int y = scanner.nextInt() - 1;
//                if () {
//
//                    continue;
//                }
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Enter a valid integer");
//            }
//        }
//
//
//
//        return new Move(x,y,cellState);

//        int boardSize = -1;
//        while (true) {
//            try {
//                boardSize = Integer.parseInt(scanner.nextLine());
//                if (boardSize<0 || boardSize>15) {
//                    System.out.println("Enter integer between 0 and 15");
//                    continue;
//                }
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Enter a valid integer");
//            }
//        }




    }

}




