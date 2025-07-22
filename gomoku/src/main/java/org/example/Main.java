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
ok so my plan is
build dumb computer player now
then abstract player
then i already have humanvshuman game so now build general game class that uses abstract player, then delete humanvshumangame class?
then smartcomputer class