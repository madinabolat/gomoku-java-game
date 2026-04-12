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
    GameIO gameIO;

    public GameSetup(GameIO gameIO){
        this.gameIO = gameIO;
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
        PlayerType playerOneType = gameIO.getPlayerType("Player One");
        PlayerType playerTwoType = gameIO.getPlayerType("Player Two");
        Player playerOne = createPlayerByType(playerOneType, CellState.PLAYER_ONE);
        Player playerTwo = createPlayerByType(playerTwoType, CellState.PLAYER_TWO);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void createBoard() {
       int boardSize = gameIO.getValidBoardDimensions();
       this.board = new Board(boardSize);
    }

    public void setNumOfConsecutiveCellsToWin() {
        this.numberOfConsecutiveCellsToWin = gameIO.getNumOfConsecutiveCellsToWin(board.boardSize);
    }

    public void initializeGame(){
        createBoard();
        setNumOfConsecutiveCellsToWin();
        initializePlayers();
        gameIO.showMessage("Game ready to start.");
        gameIO.showMessage("Players created:");
        gameIO.showMessage(playerOne.toString());
        gameIO.showMessage(playerTwo.toString());
        gameIO.showMessage("Board created:");
        gameIO.showBoard(board);
        gameIO.showMessage("Number of consecutive cells to win selected: "+ numberOfConsecutiveCellsToWin);
    }
}
