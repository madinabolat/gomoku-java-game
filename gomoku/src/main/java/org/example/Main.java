package org.example;
import org.example.game.Game;
import org.example.game.GameSetup;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameSetup gameSetup = new GameSetup(scanner);
        gameSetup.initializeGame();
        Game game = new Game(gameSetup);
        game.playGame();

    }
}
