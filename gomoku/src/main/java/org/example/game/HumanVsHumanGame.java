package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.HumanPlayer;

import java.io.IOException;
import java.util.Scanner;

public class HumanVsHumanGame {
    public Scanner scanner;
    public int boardSize;
    public Board board;
    public int numberOfConsecutiveCellsToWin;
    public HumanPlayer playerOne;
    public HumanPlayer playerTwo;
    public int currentRound;
    public String currentStatus;

    public HumanVsHumanGame(Scanner scanner){
        this.scanner = scanner;
    }

    public void createBoard(){
        System.out.println("Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square");
        boardSize = -1;
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

    public void playGame(){
        System.out.println("Choose the number of consecutive cells needed to win (for a classic Gomoku game pick 5): ");
        int numberOfConsecutiveCellsToWin = -1;
        while (true) {
            try {
                numberOfConsecutiveCellsToWin = Integer.parseInt(scanner.nextLine());
                if (numberOfConsecutiveCellsToWin<0 || numberOfConsecutiveCellsToWin>15) {
                    System.out.println("Enter integer between 0 and 15");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer");
            }
        }
        this.numberOfConsecutiveCellsToWin = numberOfConsecutiveCellsToWin;


        currentRound = 0;
        createBoard();
        while (currentStatus != "Win"){
            placeMove();
            board.printBoard();

            if (checkIfWon()==true) {
            currentStatus = "Win";
            System.out.println("Player "+ (currentRound%2+1)+" won!");
            break;
        }

        currentRound ++;

        if (board.checkIfBoardFull()==true){
            System.out.println("The board is full, game is over.");
            break;
        }
    }
    }

    public void placeMove() {
        System.out.println("Player " + (currentRound % 2 + 1) + " enter your move (example: 3,3 if you want to place your move in 3rd row, 3rd column");
        scanner.useDelimiter("\\D");
        //NEED EXCEPTION
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        if (board.checkIfCellEmpty(x,y)==true){
            board.placeMove(x,y,currentRound%2==0 ? CellState.PLAYER_ONE : CellState.PLAYER_TWO );
        } else {
            System.out.println("This cell is occupied, try again");
        }
    }

    public Boolean checkIfWon(){
        CellState[][] boardArray = board.board;
        int counterCol = 0;
        int counterRow = 0;
        int counterDiagonalRight = 0;
        int counterDiagonalLeft = 0;
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize-1; j++){
                if (boardArray[i][j] == boardArray[i][j + 1] && boardArray[i][j] != CellState.EMPTY){
                    counterCol++;
                }
                if (boardArray[j][i]==boardArray[j+1][i] && boardArray[j][i]!=CellState.EMPTY){
                    counterRow++;
                }
                if (counterCol == numberOfConsecutiveCellsToWin-1 || counterRow == numberOfConsecutiveCellsToWin-1){
                    return true;
                }
            }
            counterCol = 0;
            counterRow = 0;

        }

        for (int i = 0; i < boardSize-1; i++){
            for (int j = 0; j < boardSize-1; j++){
                if (boardArray[i][j]==boardArray[i+1][j+1] && boardArray[i][j]!=CellState.EMPTY){
                    counterDiagonalRight++;
                    int k = 0;
                    while (counterDiagonalRight < numberOfConsecutiveCellsToWin && i<boardSize-2-k && j<boardSize-2-k){
                        if (boardArray[i+1+k][j+1+k]==boardArray[i+k+2][j+k+2] && boardArray[i+1+k][j+1+k]!=CellState.EMPTY){
                            counterDiagonalRight++;
                        }
                        if (counterDiagonalRight == numberOfConsecutiveCellsToWin-1){
                            return true;
                        }
                        k++;
                    }
                }
                counterDiagonalRight = 0;

            }
        }
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if (j>0 && i < boardSize-1 && boardArray[i][j]==boardArray[i+1][j-1] && boardArray[i][j]!=CellState.EMPTY){
                    counterDiagonalLeft++;
                    int k = 0;
                    while (counterDiagonalLeft < numberOfConsecutiveCellsToWin && i<boardSize-2-k ){
                        if (boardArray[i+1+k][j-1-k]==boardArray[i+k+2][j-k-2] && boardArray[i+1+k][j-1-k]!=CellState.EMPTY){
                            counterDiagonalLeft++;
                        }
                        if (counterDiagonalLeft == numberOfConsecutiveCellsToWin-1){
                            return true;
                        }
                        k++;
                    }
                }
                counterDiagonalLeft = 0;
            }
        }
        return false;
    }

}
