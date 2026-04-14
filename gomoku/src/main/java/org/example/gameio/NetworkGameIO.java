package org.example.gameio;

import org.example.board.Board;
import org.example.player.PlayerType;

public class NetworkGameIO implements GameIO {
    @Override
    public PlayerType getPlayerType(String playerNum) {
        return null;
    }

    @Override
    public String getValidPlayerName() {
        return "";
    }

    @Override
    public int getValidBoardDimensions() {
        return 0;
    }

    @Override
    public int getNumOfConsecutiveCellsToWin(int maxValue) {
        return 0;
    }

    @Override
    public String getInput() {
        return "";
    }

    @Override
    public void showWin(String playerName) {

    }

    @Override
    public void showDraw() {

    }

    @Override
    public void showBoard(Board board) {

    }

    @Override
    public void showMessage(String message) {

    }
}
