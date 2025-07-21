package org.example.board;
import org.example.player.Move;

public class Board {
    public final int boardSize;
    public CellState[][] board;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = new CellState[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
    }

    public CellState getCell(int x, int y) {
        return board[x][y];
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j].getSymbol());
            }
            System.out.println();
        }
    }

    public boolean checkIfCellEmpty(int x, int y) {
        return board[x][y] == CellState.EMPTY;
    }

    public boolean checkIfValidCoordinates(int x, int y) {
        if (x < boardSize && x >= 0 && y >= 0 && y < boardSize) {
            return true;
        }
        return false;
    }


    public boolean checkIfBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == CellState.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeMove(Move move) {
        if (checkIfCellEmpty(move.x, move.y)) {
            board[move.x][move.y] = move.cellState;
        } else {
            System.out.println("This cell is occupied. Please try again.");
        }
    }
}
