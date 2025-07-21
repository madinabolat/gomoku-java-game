package org.example.game;
import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.HumanPlayer;
import org.example.player.Move;
import java.util.Scanner;

public class HumanVsHumanGame {
    public Scanner scanner;
    public Board board;
    public int numberOfConsecutiveCellsToWin;
    public HumanPlayer playerOne;
    public HumanPlayer playerTwo;
    public WinChecker winChecker;

    public HumanVsHumanGame(Scanner scanner) {
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

    public void initializeWinChecker() {
        this.winChecker = new WinChecker(board, numberOfConsecutiveCellsToWin);
    }

    public HumanPlayer getCurrentPlayer(int currentRound) {
        if (currentRound % 2 == 0) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public Move getValidMove(HumanPlayer currentPlayer) {
        Move currentMove;
        while (true) {
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
        return currentMove;
    }

    public void initializeGame() {
        initializePlayers();
        createBoard();
        getNumOfConsecutiveCellsToWin();
        initializeWinChecker();
    }

    public void playGame() {
        int currentRound = 0;
        HumanPlayer currentPlayer;
        Move currentMove;
        GameState gameState;

        gameState = GameState.IN_PROGRESS;

        while (gameState != GameState.WIN) {
            currentPlayer = getCurrentPlayer(currentRound);
            currentMove = getValidMove(currentPlayer);
            board.placeMove(currentMove);
            board.printBoard();

            if (winChecker.isWinningMove(currentMove.x, currentMove.y)) {
                System.out.println(currentPlayer.name + " won!");
                gameState = GameState.WIN;
                break;
            }

            currentRound++;

            if (board.checkIfBoardFull()) {
                System.out.println("The board is full, game is over.");
                gameState = GameState.DRAW;
                break;
            }
        }
    }

}
