package org.example.game;
import org.example.board.Board;
import org.example.board.CellState;

public class WinChecker {
    public Board board;
    public int numberOfConsecutiveCellsToWin;
    private final static int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};


    public WinChecker(Board board, int numberOfConsecutiveCellsToWin) {
        this.board = board;
        this.numberOfConsecutiveCellsToWin = numberOfConsecutiveCellsToWin;
    }

    public int countConsecutive(int x, int y, int dx, int dy) {
        CellState[][] boardArray = board.board;

        if (boardArray[x][y] == CellState.EMPTY) {
            System.out.println("The starting cell is empty.");
            return 0;
        }

        int counter = 0;
        int i = 0;

        while (x + i * dx < board.boardSize && x + i * dx > -1 && y + i * dy > -1 && y + i * dy < board.boardSize) {
            if (boardArray[x][y] != boardArray[x + i * dx][y + i * dy]) {
                break;
            }
            counter++;
            i++;
        }

        return counter;
    }

    //dx,dy
    //horizontal -> (0,1), (0,-1)
    //vertical -> (1,0), (-1,0)
    //diagonal -> (1,1), (-1,-1), (-1,1), (1,-1)

    public boolean isWinningMove(int x, int y) {
        int lengthConsecutiveCells = 0;
        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            lengthConsecutiveCells = countConsecutive(x, y, dx, dy) + countConsecutive(x, y, -dx, -dy) - 1;
            if (lengthConsecutiveCells == numberOfConsecutiveCellsToWin) {
                return true;
            }
        }
        return false;
    }
}