package org.example.game;

import org.example.board.Board;
import org.example.player.HumanPlayer;
import org.example.player.Move;
import org.example.player.Player;

public class Game {
    GameSetup gameSetup;
    Board board;
    WinChecker winChecker;

    public Game(GameSetup gameSetup){
        this.gameSetup = gameSetup;
        this.board = gameSetup.board;
        this.winChecker = new WinChecker(board, gameSetup.numberOfConsecutiveCellsToWin);
    }

    public Player getCurrentPlayer(int currentRound) {
        if (currentRound % 2 == 0) {
            return gameSetup.playerOne;
        } else {
            return gameSetup.playerTwo;
        }
    }

    public Move getValidMove(Player currentPlayer) { //this should be changed for computer
        Move currentMove;
        while (true) {
            currentMove = currentPlayer.getMove(board);
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

    public void playGame() {
        int currentRound = 0;
        Player currentPlayer;
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
