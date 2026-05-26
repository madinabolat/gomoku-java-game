package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.gameio.GameIO;
import org.example.player.*;

public class GameSetup {
    Player playerOne;
    Player playerTwo;
    Board board;
    int numberOfConsecutiveCellsToWin;
    GameIO gameIOPlayerOne;
    GameIO gameIOPlayerTwo;


    public GameSetup(GameIO gameIOPlayerOne, GameIO gameIOPlayerTwo){
        this.gameIOPlayerOne = gameIOPlayerOne;
        this.gameIOPlayerTwo = gameIOPlayerTwo;
    }

    public Player createPlayerByType(PlayerType playerType, CellState cellState, GameIO gameIO){
        switch (playerType){
            case PlayerType.HUMAN:
                return createHumanPlayer(cellState, gameIO);
            case PlayerType.DUMB_COMPUTER:
                return createDumbComputerPlayer(cellState);
            case PlayerType.SMART_COMPUTER:
                return createSmartComputerPlayer(cellState, new WinChecker(board,numberOfConsecutiveCellsToWin));
            default:
                throw new IllegalArgumentException("Player type is not supported.");
        }
    }

    public Player createHumanPlayer(CellState cellState, GameIO gameIO) {
        String name = gameIO.getValidPlayerName();
        return new HumanPlayer(gameIO, cellState, name);
    }

    public Player createDumbComputerPlayer(CellState cellState) {
        return new DumbComputerPlayer(cellState);
    }

    public Player createSmartComputerPlayer(CellState cellState, WinChecker winChecker) {
        return new SmartComputerPlayer(cellState, winChecker);
    }

    public void initializePlayers(){
        PlayerType playerOneType = gameIOPlayerOne.getPlayerType("Player One");
        PlayerType playerTwoType = gameIOPlayerTwo.getPlayerType("Player Two");
        Player playerOne = createPlayerByType(playerOneType, CellState.PLAYER_ONE, gameIOPlayerOne);
        Player playerTwo = createPlayerByType(playerTwoType, CellState.PLAYER_TWO, gameIOPlayerTwo);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void createBoard() {
       int boardSize = gameIOPlayerOne.getValidBoardDimensions();
       this.board = new Board(boardSize);
    }

    public void setNumOfConsecutiveCellsToWin() {
        this.numberOfConsecutiveCellsToWin = gameIOPlayerOne.getNumOfConsecutiveCellsToWin(board.boardSize);
    }

    public void display(GameIO gameIO){
        gameIO.showMessage("Game ready to start.");
        gameIO.showMessage("Players created:");
        gameIO.showMessage(playerOne.toString());
        gameIO.showMessage(playerTwo.toString());
        gameIO.showMessage("Board created:");
        gameIO.showBoard(board);
        gameIO.showMessage("Number of consecutive cells to win selected: "+ numberOfConsecutiveCellsToWin);
    }

    public void initializeGame(){
        createBoard();
        setNumOfConsecutiveCellsToWin();
        initializePlayers();
        display(gameIOPlayerOne);
        display(gameIOPlayerTwo);
    }
}
