package org.example.gameio;

import org.example.board.Board;
import org.example.player.*;

public interface GameIO {
    public PlayerType getPlayerType(String playerNum);

    public String getValidPlayerName();

    public int getValidBoardDimensions();

    public int getNumOfConsecutiveCellsToWin(int maxValue);

    public String getInput();

    public void showWin(String playerName);

    public void showDraw();

    public void showBoard(Board board);

    public void showMessage(String message);

}
