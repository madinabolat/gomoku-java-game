package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.HumanPlayer;

import java.util.Scanner;

public class GameSetup {

    Scanner scanner;

    public GameSetup(Scanner scanner){
        this.scanner = scanner;
    }

    public void initializePlayers() {
        String nameOne = getValidPlayerName("Player One");
        String nameTwo = getValidPlayerName("Player Two");
        this.playerOne = new HumanPlayer(scanner, CellState.PLAYER_ONE, nameOne);
        this.playerTwo = new HumanPlayer(scanner, CellState.PLAYER_TWO, nameTwo);
    }

    public String getValidPlayerName(String playerNum) {
        System.out.print("Enter name for " + playerNum + ": ");
        String name;
        while (true) {
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
                continue;
            }
            break;
        }
        return name;
    }

    public void createBoard() {
        System.out.println("Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square");
        int boardSize = -1;
        while (true) {
            try {
                boardSize = Integer.parseInt(scanner.nextLine());
                if (boardSize < 0 || boardSize > 15) {
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

    public void getNumOfConsecutiveCellsToWin() {
        System.out.println("Choose the number of consecutive cells needed to win (for a classic Gomoku game pick 5): ");
        int numberOfConsecutiveCellsToWin = -1;
        while (true) {
            try {
                numberOfConsecutiveCellsToWin = Integer.parseInt(scanner.nextLine());
                if (numberOfConsecutiveCellsToWin < 0 || numberOfConsecutiveCellsToWin > 15) {
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

}
