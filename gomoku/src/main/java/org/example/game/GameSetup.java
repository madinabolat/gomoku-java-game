package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.*;

import java.util.Scanner;

public class GameSetup {
    Scanner scanner;
    Player playerOne;
    Player playerTwo;
    Board board;
    int numberOfConsecutiveCellsToWin;

    public GameSetup(Scanner scanner){
        this.scanner = scanner;
    }

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

    public Player createPlayerByType(PlayerType playerType, CellState cellState){
        switch (playerType){
            case PlayerType.HUMAN:
                return createHumanPlayer(cellState);
            case PlayerType.DUMB_COMPUTER:
                return createDumbComputerPlayer(cellState);
            case PlayerType.SMART_COMPUTER:
                return createSmartComputerPlayer(cellState, new WinChecker(board,numberOfConsecutiveCellsToWin));
            default:
                throw new IllegalArgumentException("Player type is not supported.");
        }
    }

    public Player createHumanPlayer(CellState cellState) {
        String name = getValidPlayerName();
        return new HumanPlayer(scanner, cellState, name);
    }

    public Player createDumbComputerPlayer(CellState cellState) {
        return new DumbComputerPlayer(cellState);
    }

    public Player createSmartComputerPlayer(CellState cellState, WinChecker winChecker) {
        return new SmartComputerPlayer(cellState, winChecker);
    }


    public void initializePlayers(){
        PlayerType playerOneType = getPlayerType("Player One");
        PlayerType playerTwoType = getPlayerType("Player Two");
        Player playerOne = createPlayerByType(playerOneType, CellState.PLAYER_ONE);
        Player playerTwo = createPlayerByType(playerTwoType, CellState.PLAYER_TWO);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }


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

    public void createBoard() {
       int boardSize = getValidBoardDimensions();
       this.board = new Board(boardSize);
    }

    public int getValidBoardDimensions() {
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
        return boardSize;
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

    public void initializeGame(){
        createBoard(); // need this before initializePlayers() because SmartComputerPlayer needs winChecker which is created based on board and numOfConsecutiveCellsToWin
        getNumOfConsecutiveCellsToWin();
        initializePlayers();
        System.out.println("Game ready to start.");
        System.out.println("Players created:");
        System.out.println(playerOne.toString());
        System.out.println(playerTwo.toString());
        System.out.println("Board created:");
        board.printBoard();
        System.out.println("Number of consecutive cells to win selected: "+ numberOfConsecutiveCellsToWin);
    }
}
