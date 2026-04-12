package org.example;
import org.example.game.Game;
import org.example.game.GameSetup;
import org.example.gameio.ConsoleGameIO;
import org.example.gameio.GameIO;

public class Main {

    public static void main(String[] args) {
        GameIO gameIO = new ConsoleGameIO();
        GameSetup gameSetup = new GameSetup(gameIO);
        gameSetup.initializeGame();
        Game game = new Game(gameSetup);
        game.playGame();
    }
}
