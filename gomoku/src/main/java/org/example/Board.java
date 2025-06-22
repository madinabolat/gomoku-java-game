package org.example;

public class Board {
    int boardSize;
    int numConsecutiveCellsToWin;
    String[][] board;
    String currentStatus;

    Board(int boardSize, int numConsecutiveCellsToWin){
        this.boardSize = boardSize;
        this.numConsecutiveCellsToWin = numConsecutiveCellsToWin;
        this.board = new String[boardSize][boardSize];
        this.currentStatus = "";
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                board[i][j]="_";
            }
        }
    }

    public void printBoard(){
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public void placeMove(int x, int y, String moveType){
        board[x][y]=moveType;
    }

    public Boolean checkIfCellEmpty(int x, int y){
        if (board[x][y] == "_"){
            return true;
        }
        return false;
    }

    public Boolean checkIfWon(){
        int counterCol = 0;
        int counterRow = 0;
        int counterDiagonalRight = 0;
        int counterDiagonalLeft = 0;
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize-1; j++){
                if (board[i][j]==board[i][j+1] && board[i][j]!="_"){
                    counterCol++;
                }
                if (board[j][i]==board[j+1][i] && board[j][i]!="_"){
                    counterRow++;
                }
                if (counterCol == numConsecutiveCellsToWin-1 || counterRow == numConsecutiveCellsToWin-1){
                    return true;
                }
            }
            counterCol = 0;
            counterRow = 0;

        }

        //works only if numConsectuiveCellsToWin == boardSize
//        for (int i = 0; i < boardSize-1; i++){
//                if (board[i][i]==board[i+1][i+1] && board[i][i]!="_"){
//                    counterDiagonalRight++;
//                }
//                if (board[i][boardSize-1-i]==board[i+1][boardSize-2-i] && board[i][boardSize-1-i]!="_"){
//                    counterDiagonalLeft++;
//                }
//                if (counterDiagonalRight == numConsecutiveCellsToWin-1 || counterDiagonalLeft == numConsecutiveCellsToWin-1){
//                    return true;
//                }
//            }

        for (int i = 0; i < boardSize-1; i++){
            for (int j = 0; j < boardSize-1; j++){
                if (board[i][j]==board[i+1][j+1] && board[i][j]!="_"){
                    counterDiagonalRight++;
                    int k = 0;
                    while (counterDiagonalRight < numConsecutiveCellsToWin && i<boardSize-2-k && j<boardSize-2-k){
                        if (board[i+1+k][j+1+k]==board[i+k+2][j+k+2] && board[i+1+k][j+1+k]!="_"){
                            counterDiagonalRight++;
                        }
                        if (counterDiagonalRight == numConsecutiveCellsToWin-1){
                            return true;
                        }
                        k++;
                    }
                }
                counterDiagonalRight = 0;

            }
        }
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if (j>0 && i < boardSize-1 && board[i][j]==board[i+1][j-1] && board[i][j]!="_"){
                    counterDiagonalLeft++;
                    int k = 0;
                    while (counterDiagonalLeft < numConsecutiveCellsToWin && i<boardSize-2-k ){
                        if (board[i+1+k][j-1-k]==board[i+k+2][j-k-2] && board[i+1+k][j-1-k]!="_"){
                            counterDiagonalLeft++;
                        }
                        if (counterDiagonalLeft == numConsecutiveCellsToWin-1){
                            return true;
                        }
                        k++;
                    }
                }
                counterDiagonalLeft = 0;
            }
        }
        return false;
    }

    public Boolean checkIfBoardFull(){
        int counter = 0;
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if (board[i][j]=="_"){
                    counter++;
                }
            }

        }
        if (counter==0){
            return true;
        }
        return false;
    }
}
