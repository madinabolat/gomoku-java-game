package org.example.gameio;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.PlayerType;

public class NetworkGameIO implements GameIO {
    @Override
    public PlayerType getPlayerType(String playerNum) {
        return PlayerType.HUMAN;
        //only humans play for now
    }

    @Override
    public String getValidPlayerName() {
        //server sends: asks for player name
        //client sends name
        //server validates input
        //server returns name

        //server sends: "ENTER_NAME"
        //client shows: "Enter name for a player:"
        //client sends: "Alice"
        //server validates. If empty, sends "EMPTY_NAME_ERROR"
        //if error, client sends: "Name cant be empty. Please enter again".
        //server returns name
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
        //server sends: "ENTER_BOARD_SIZE_MAX_15"
        //client shows: "Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square"
        //client sends: 15
        //server validates. if not valid, sends "ENTER_VALID_INTEGER"

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
