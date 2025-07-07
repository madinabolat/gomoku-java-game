package org.example.board;

public enum CellState {
    EMPTY('_'),
    PLAYER_ONE('w'),
    PLAYER_TWO('b');

    final char symbol;

    CellState(char symbol){
      this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}