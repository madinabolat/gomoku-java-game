package org.example.gameio;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.PlayerType;

public abstract class BaseGameIO implements GameIO{

    abstract void sendMessage(String message);
    abstract String receiveLine();


    @Override
    public PlayerType getPlayerType(String playerNum) {
        sendMessage("Enter " + playerNum + " type (H for human, DC for dumb computer, SC for smart computer): ");
        String type;
        while (true) {
            type = receiveLine().trim();
            if (type.isEmpty()) {
                sendMessage("Input cannot be empty. Please enter a valid input.");
                continue;
            }
            if (!(type.equals("H") || type.equals("DC") || type.equals("SC"))) {
                sendMessage("Input can be one of H, DC or SC only.");
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
        sendMessage("Enter name for player: ");
        String name;
        while (true) {
            name = receiveLine().trim();
            if (name.isEmpty()) {
                sendMessage("Name cannot be empty. Please enter a valid name.");
                continue;
            }
            break;
        }
        return name;
    }

    @Override
    public int getValidBoardDimensions() {
        sendMessage("Enter the size of the board (max: 15), for example: 15 for a 15x15 board (a classic gomoku game). The board will always be square");
        int boardSize = -1;
        while (true) {
            try {
                boardSize = Integer.parseInt(receiveLine());
                if (boardSize < 3 || boardSize > 15) {
                    sendMessage("Enter integer between 3 and 15");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                sendMessage("Enter a valid integer");
            }
        }
        return boardSize;
    }

    @Override
    public int getNumOfConsecutiveCellsToWin(int maxValue) {
        sendMessage("Choose the number of consecutive cells needed to win (for a classic Gomoku game pick 5): ");
        int numberOfConsecutiveCellsToWin = -1;

        while (true) {
            try {
                numberOfConsecutiveCellsToWin = Integer.parseInt(receiveLine());
                if (numberOfConsecutiveCellsToWin < 2 || numberOfConsecutiveCellsToWin > maxValue) {
                    sendMessage("Enter integer between 2 and " + maxValue);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                sendMessage("Enter a valid integer");
            }
        }
        return numberOfConsecutiveCellsToWin;
    }

    @Override
    public String getInput() {
        return receiveLine();
    }

    @Override
    public void showWin(String playerName) {
        sendMessage(playerName + " won!");
    }

    @Override
    public void showDraw() {
        sendMessage("The board is full, game is over.");
    }

    public abstract void showBoard(Board board);

    @Override
    public void showMessage(String message) {
        sendMessage(message);
    }
}
