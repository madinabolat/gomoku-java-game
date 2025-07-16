package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.HumanPlayer;
import org.example.player.Move;
import org.example.player.Player;
import java.io.IOException;
import java.util.Scanner;

public class HumanVsHumanGame {
    public Scanner scanner;
    public Board board;
    public int numberOfConsecutiveCellsToWin;
    public HumanPlayer playerOne;
    public HumanPlayer playerTwo;

    public HumanVsHumanGame(Scanner scanner){
        this.scanner = scanner;
    }

    public void initializePlayers(){
        String nameOne = getValidPlayerName("Player One");
        String nameTwo = getValidPlayerName("Player Two");
        this.playerOne = new HumanPlayer(scanner,CellState.PLAYER_ONE, nameOne);
        this.playerTwo = new HumanPlayer(scanner,CellState.PLAYER_TWO, nameTwo);
    }

    public String getValidPlayerName(String playerNum){
        System.out.print("Enter name for " + playerNum + ": ");
        String name;
        while (true){
            name = scanner.nextLine().trim();
            if (name.isEmpty()){
                System.out.println("Name cannot be empty. Please enter a valid name.");
                continue;
            }
            break;
        }
        return name;
    }

    public void createBoard(){
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

    public void getNumOfConsecutiveCellsToWin(){
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
    }


    public void playGame(){
        createBoard();
        getNumOfConsecutiveCellsToWin();

        int currentRound = 0;
        HumanPlayer currentPlayer;
        Move currentMove;
        String currentStatus = new String();
        while (currentStatus != "Win"){
            if (currentRound % 2 == 0) {
                currentPlayer = playerOne;
            } else {
                currentPlayer = playerTwo;
            }

            while (true){
                currentMove = currentPlayer.getMove();
                if (!board.checkIfValidCoordinates(currentMove.x, currentMove.y)) {
                    System.out.println("This cell is out of bounds. Please enter coordinates within the board size " + board.boardSize + "x" + board.boardSize);
                    continue;
                }
                if (!board.checkIfCellEmpty(currentMove.x, currentMove.y)) {
                    System.out.println("This cell is occupied. Please enter again");
                    continue;
                }
                break;
            }

            board.placeMove(currentMove);
            board.printBoard();

            //change checkIfWon - so that it has move as input
            //add winchecker object

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




}
