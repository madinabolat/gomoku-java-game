package org.example.game;

import org.example.board.Board;
import org.example.gameio.GameIO;
import org.example.player.Move;
import org.example.player.Player;
import org.example.player.PlayerType;

public class Game {
    GameSetup gameSetup;
    Board board;
    WinChecker winChecker;
    GameIO gameIO;

    public Game(GameSetup gameSetup){
        this.gameSetup = gameSetup;
        this.board = gameSetup.board;
        this.winChecker = new WinChecker(board, gameSetup.numberOfConsecutiveCellsToWin);
        this.gameIO = gameSetup.gameIO;
    }

    public Player getCurrentPlayer(int currentRound) {
        if (currentRound % 2 == 0) {
            return gameSetup.playerOne;
        } else {
            return gameSetup.playerTwo;
        }
    }

    public Move getValidMove(Player currentPlayer) {
        Move currentMove;
        while (true) {
            currentMove = currentPlayer.getMove(board);
            if (currentPlayer.playerType.equals(PlayerType.HUMAN)){
                if (!board.checkIfValidCoordinates(currentMove.x, currentMove.y)) {
                    gameIO.showMessage("This cell is out of bounds. Please enter coordinates within the board size " + board.boardSize + "x" + board.boardSize);
                    continue;
                }
                if (!board.checkIfCellEmpty(currentMove.x, currentMove.y)) {
                    gameIO.showMessage("This cell is occupied. Please enter again");
                    continue;
                }
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
            gameIO.showBoard(board);

            if (winChecker.isWinningMove(currentMove.x, currentMove.y)) {
                gameIO.showWin(currentPlayer.name);
                gameState = GameState.WIN;
                break;
            }

            currentRound++;

            if (board.checkIfBoardFull()) {
                gameIO.showDraw();
                gameState = GameState.DRAW;
                break;
            }
        }
    }


}
