package org.example.gameio;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.player.PlayerType;

import java.util.Scanner;

public class ConsoleGameIO extends BaseGameIO{
    Scanner scanner;

    public ConsoleGameIO(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    void sendMessage(String message){
        System.out.println(message);
    }

    @Override
    String receiveLine(){
        return scanner.nextLine();
    }

    @Override
    public void showBoard(Board board) {
        CellState[][] boardArray = board.board;
        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {
                System.out.print(boardArray[i][j].getSymbol());
            }
            System.out.println();
        }
    }

}
