package org.example.gameio;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.PlayerType;

import java.util.Scanner;

public class ConsoleGameIO implements GameIO{
    Scanner scanner;

    public ConsoleGameIO(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public PlayerType getPlayerType(String playerNum) {
        System.out.print("Enter " + playerNum + " type (H for human, DC for dumb computer, SC for smart computer): ");
        String type;
        while (true) {
            type = scanner.nextLine().trim();
            if (type.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid input.");
                continue;
            }
            if (!(type.equals("H") || type.equals("DC") || type.equals("SC"))) {
                System.out.println("Input can be one of H, DC or SC only.");
                continue;
            }
            break;
        }
        switch(type){
            case "H":
                return PlayerType.HUMAN;
            case "DC":
                return PlayerType.DUMB_COMPUTER;
            case "SC":
                return PlayerType.SMART_COMPUTER;
            default:
                throw new IllegalArgumentException("Player type is not supported.");
        }
    }

    @Override
    public String getValidPlayerName() {
        System.out.print("Enter name for player: ");
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

    @Override
    public int getValidBoardDimensions() {
        System.out.println("Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square");
        int boardSize = -1;
        while (true) {
            try {
                boardSize = Integer.parseInt(scanner.nextLine());
                if (boardSize < 3 || boardSize > 15) {
                    System.out.println("Enter integer between 3 and 15");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer");
            }
        }
        return boardSize;
    }

    @Override
    public int getNumOfConsecutiveCellsToWin(int maxValue) {
        System.out.println("Choose the number of consecutive cells needed to win (for a classic Gomoku game pick 5): ");
        int numberOfConsecutiveCellsToWin = -1;

        while (true) {
            try {
                numberOfConsecutiveCellsToWin = Integer.parseInt(scanner.nextLine());
                if (numberOfConsecutiveCellsToWin < 2 || numberOfConsecutiveCellsToWin > maxValue) {
                    System.out.println("Enter integer between 2 and " + maxValue);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer");
            }
        }
        return numberOfConsecutiveCellsToWin;
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public void showWin(String playerName) {
        System.out.println(playerName + " won!");
    }

    @Override
    public void showDraw() {
        System.out.println("The board is full, game is over.");
    }

    @Override
    public void showBoard(Board board) {
        CellState[][] boardArray = board.board;
        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {
                System.out.print(boardArray[i][j].getSymbol());
            }
            System.out.println();
        }

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
