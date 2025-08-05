package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.DumbComputerPlayer;
import org.example.player.HumanPlayer;
import org.example.player.Player;
import org.example.player.PlayerType;

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
        PlayerType playerType = null;
        switch(type){
            case "H":
                playerType = PlayerType.HUMAN;
                break;
            case "DC":
                playerType = PlayerType.DUMB_COMPUTER;
                break;
            case "SC":
                playerType = PlayerType.SMART_COMPUTER;
                break;
        }
        return playerType;
    }

    public Player createPlayerByType(PlayerType playerType){
        switch (playerType){
            case PlayerType.HUMAN:
                return createHumanPlayer();
            case PlayerType.DUMB_COMPUTER:
                return createDumbComputerPlayer();
            case PlayerType.SMART_COMPUTER:
                throw new UnsupportedOperationException("Not supported yet");
            default:
                throw new IllegalArgumentException("Player type is not supported.");
        }
    }

    public Player createHumanPlayer() {
        String name = getValidPlayerName();
        return new HumanPlayer(scanner, CellState.PLAYER_ONE, name); // WRONG - DONT KNOW PLAYER TYPE YET
    }

    public Player createDumbComputerPlayer() {
        return new DumbComputerPlayer(CellState.PLAYER_ONE); // WRONG - DONT KNOW PLAYER TYPE YET
    }


    public void initializePlayers(){
        PlayerType playerOneType = getPlayerType("Player One");
        PlayerType playerTwoType = getPlayerType("Player Two");
        Player playerOne = createPlayerByType(playerOneType);
        Player playerTwo = createPlayerByType(playerTwoType);
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
       //board.printBoard();
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
        initializePlayers();
        createBoard();
        getNumOfConsecutiveCellsToWin();
        System.out.println("Game ready to start.");
        System.out.println("Players created:");
        System.out.println(playerOne.toString());
        System.out.println(playerTwo.toString());
        System.out.println("Board created:");
        board.printBoard();
        System.out.println("Number of consecutive cells to win selected: "+ numberOfConsecutiveCellsToWin);
    }
}
