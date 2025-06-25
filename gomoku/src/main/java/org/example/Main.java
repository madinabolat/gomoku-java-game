package org.example;

import org.example.board.Board;
import org.example.game.HumanVsHumanGame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HumanVsHumanGame game = new HumanVsHumanGame(scanner);
        game.testMethod();


//Refactor!


    }


//
//
//
//
//
//    Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square");
//    //NEED EXCEPTION
//    int boardSize = Integer.parseInt(scanner.nextLine());
//        System.out.println("Choose the number of consecutive cells needed to win (for a classic Gomoku game pick 5): ");
//    //NEED EXCEPTION
//    int numConsecutiveCellsToWin = Integer.parseInt(scanner.nextLine());
//    Board gameBoard = new Board(boardSize, numConsecutiveCellsToWin);
//        gameBoard.printBoard();
//
//    int round = 0;
//    String moveType;
//
//        while (gameBoard.currentStatus != "Win"){
//        System.out.println("Player "+ (round%2+1)+" enter your move (example: 3,3 if you want to place your move in 3rd row, 3rd column");
//        scanner.useDelimiter("\\D");
//        //NEED EXCEPTION
//        int x = scanner.nextInt()-1;
//        int y = scanner.nextInt()-1;
//
//        if (round % 2==0) {
//            moveType = "w";
//        } else {
//            moveType = "b";
//        }
//
//        if (gameBoard.checkIfCellEmpty(x,y)==true){
//            gameBoard.placeMove(x,y,moveType);
//        } else {
//            System.out.println("This cell is occupied, try again");
//            continue;
//        }
//
//        gameBoard.printBoard();
//
//        if (gameBoard.checkIfWon()==true) {
//            gameBoard.currentStatus = "Win";
//            System.out.println("Player "+ (round%2+1)+" won!");
//            break;
//        }
//
//        round ++;
//
//        if (gameBoard.checkIfBoardFull()==true){
//            System.out.println("The board is full, game is over.");
//            break;
//        }
//    }
//}
//}



        }
