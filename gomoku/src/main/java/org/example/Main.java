package org.example;
import org.example.game.HumanVsHumanGame;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HumanVsHumanGame game = new HumanVsHumanGame(scanner);
        game.initializeGame();
        game.playGame();
    }
}
