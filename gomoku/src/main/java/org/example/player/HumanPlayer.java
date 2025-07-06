package org.example.player;

import org.example.board.CellState;

public class HumanPlayer {





    public void placeMove() {
        System.out.println("Player " + (currentRound % 2 + 1) + " enter your move (example: 3,3 if you want to place your move in 3rd row, 3rd column");
        scanner.useDelimiter("\\D");
        //NEED EXCEPTION
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        if (board.checkIfCellEmpty(x,y)==true){
            board.placeMove(x,y,currentRound%2==0 ? CellState.PLAYER_ONE : CellState.PLAYER_TWO );
        } else {
            System.out.println("This cell is occupied, try again");
        }
    }




}




