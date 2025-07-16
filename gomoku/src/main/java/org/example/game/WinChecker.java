package org.example.game;

import org.example.board.Board;
import org.example.board.CellState;

public class WinChecker {
    public Board board;
    public int numberOfConsecutiveCellsToWin;


    public WinChecker(Board board, int numberOfConsecutiveCellsToWin){
        this.board = board;
        this.numberOfConsecutiveCellsToWin = numberOfConsecutiveCellsToWin;
    }

    public Boolean hasLine(int x, int y, int dx, int dy, int n){
        CellState[][] boardArray = board.board;

        if (boardArray[x][y] == CellState.EMPTY){
            System.out.println("The starting cell is empty.");
            return false;
        }

        if (y+dy*(n-1)>board.boardSize-1 || x+dx*(n-1)>board.boardSize-1 || y+dy*(n-1)<0 || x+dx*(n-1)<0){
            System.out.println("The line is out of bounds");
            return false;
        }

        for (int i = 1; i < n; i++){
            if (boardArray[x][y] != boardArray[x + i*dx][y + i*dy]) {
                return false;
            }
        }
        return true;
    }

    //dx,dy
    //horizontal -> (0,1), (0,-1)
    //vertical -> (1,0), (-1,0)
    //diagonal -> (1,1), (1,-1), (-1,-1), (-1,1)


    //check so that it counts not just starting from that cell to the right / left / diagonal numofconssecutive cells but also that cell some to the left, some to the right together line

    public Boolean isWinning(int x, int y){
        for (int i = -1; i<=1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (hasLine(x,y,i,j,numberOfConsecutiveCellsToWin) && !(i==0 && j==0)){
                    return true;
                }
            }
        }
        return false;
    }

}
