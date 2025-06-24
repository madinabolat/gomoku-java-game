package org.example.game;

import org.example.board.Board;

import java.io.IOException;
import java.util.Scanner;

public class HumanVsHumanGame {
    public Scanner scanner;
    public Board board;

    public HumanVsHumanGame(Scanner scanner){
        this.scanner = scanner;
    }

    public void testMethod(){
        System.out.println("Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square");
        int boardSize = -1;
        while (true) {
            try {
                boardSize = Integer.parseInt(scanner.nextLine());
                if (boardSize<0 || boardSize>15) {
                    System.out.println("Enter integer between 0 and 15");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer");
            }
        }
        this.board = new Board(boardSize);
        board.printBoard();


    }

    //int numConsecutiveCellsToWin;

//
//    public Boolean checkIfWon(){
//        int counterCol = 0;
//        int counterRow = 0;
//        int counterDiagonalRight = 0;
//        int counterDiagonalLeft = 0;
//        for (int i = 0; i < boardSize; i++){
//            for (int j = 0; j < boardSize-1; j++){
//                if (board[i][j].equals(board[i][j+1]) && !board[i][j].equals("_")){
//                    counterCol++;
//                }
//                    counterRow++;
//                }
//                if (counterCol == numConsecutiveCellsToWin-1 || counterRow == numConsecutiveCellsToWin-1){
//                    return true;
//                }
//            }
//            counterCol = 0;
//            counterRow = 0;
//
//        }
//
//        for (int i = 0; i < boardSize-1; i++){
//            for (int j = 0; j < boardSize-1; j++){
//                if (board[i][j].equals(board[i+1][j+1]) && !board[i][j].equals("_")){
//                    counterDiagonalRight++;
//                    int k = 0;
//                    while (counterDiagonalRight < numConsecutiveCellsToWin && i<boardSize-2-k && j<boardSize-2-k){
//                        if (board[i+1+k][j+1+k].equals(board[i+k+2][j+k+2]) && !board[i+1+k][j+1+k].equals("_")){
//                            counterDiagonalRight++;
//                        }
//                        if (counterDiagonalRight == numConsecutiveCellsToWin-1){
//                            return true;
//                        }
//                        k++;
//                    }
//                }
//                counterDiagonalRight = 0;
//
//            }
//        }
//        for (int i = 0; i < boardSize; i++){
//            for (int j = 0; j < boardSize; j++){
//                if (j>0 && i < boardSize-1 && board[i][j].equals(board[i+1][j-1]) && board[i][j].equals("_")){
//                    counterDiagonalLeft++;
//                    int k = 0;
//                    while (counterDiagonalLeft < numConsecutiveCellsToWin && i<boardSize-2-k ){
//                        if (board[i+1+k][j-1-k].equals(board[i+k+2][j-k-2]) && board[i+1+k][j-1-k].equals("_")){
//                            counterDiagonalLeft++;
//                        }
//                        if (counterDiagonalLeft == numConsecutiveCellsToWin-1){
//                            return true;
//                        }
//                        k++;
//                    }
//                }
//                counterDiagonalLeft = 0;
//            }
//        }
//        return false;
//    }


}
