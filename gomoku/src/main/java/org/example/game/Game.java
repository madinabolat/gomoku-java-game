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
    GameIO gameIOPlayerOne;
    GameIO gameIOPlayerTwo;

    public Game(GameSetup gameSetup){
        this.gameSetup = gameSetup;
        this.board = gameSetup.board;
        this.winChecker = new WinChecker(board, gameSetup.numberOfConsecutiveCellsToWin);
        this.gameIOPlayerOne = gameSetup.gameIOPlayerOne;
        this.gameIOPlayerTwo = gameSetup.gameIOPlayerTwo;
    }

    public Player getCurrentPlayer(int currentRound) {
        if (currentRound % 2 == 0) {
            return gameSetup.playerOne;
        } else {
            return gameSetup.playerTwo;
        }
    }

    public GameIO getCurrentGameIO(int currentRound) {
        if (currentRound % 2 == 0) {
            return gameSetup.gameIOPlayerOne;
        } else {
            return gameSetup.gameIOPlayerTwo;
        }
    }

    public Move getValidMove(Player currentPlayer, GameIO gameIO) {
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
        //TO DO
//        1. Go through playGame() and label each gameIO call — "current player only" or "both"
//        2. Then update Game to have two gameIO fields
//        3. Then start on NetworkGameIO — give it a socket and implement getInput() / showMessage() / showBoard() using streams instead of System.out

        int currentRound = 0;
        Player currentPlayer;
        Move currentMove;
        GameIO currentGameIO;
        GameState gameState;
        gameState = GameState.IN_PROGRESS;

        while (gameState != GameState.WIN) {
            currentPlayer = getCurrentPlayer(currentRound);
            currentGameIO = getCurrentGameIO(currentRound);
            currentMove = getValidMove(currentPlayer, currentGameIO);
            board.placeMove(currentMove);
            gameIOPlayerOne.showBoard(board);
            gameIOPlayerTwo.showBoard(board);

            if (winChecker.isWinningMove(currentMove.x, currentMove.y)) {
                gameIOPlayerOne.showWin(currentPlayer.name);
                gameIOPlayerTwo.showWin(currentPlayer.name);
                gameState = GameState.WIN;
                break;
            }

            currentRound++;

            if (board.checkIfBoardFull()) {
                gameIOPlayerOne.showDraw();
                gameIOPlayerTwo.showDraw();
                gameState = GameState.DRAW;
                break;
            }
        }
    }


}
