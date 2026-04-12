package org.example.player;
import org.example.board.Board;
import org.example.board.CellState;
import org.example.gameio.GameIO;

public class HumanPlayer extends Player{
    GameIO gameIO;

    public HumanPlayer(GameIO gameIO, CellState cellState, String name) {
        super(cellState, name, PlayerType.HUMAN);
        this.gameIO = gameIO;
    }

    @Override
    public int[] generateCoordinates(Board board){
        gameIO.showMessage(name + ", enter your move in 'row,column' format " +
                "(example: '3,3' if you want to place your move in 3rd row, 3rd column). Row, column numbers start from 1.");

        String input;
        String[] coordinates;
        int x = -1;
        int y = -1;

        while (true) {
            input = gameIO.getInput();
            coordinates = input.split(",");

            if (coordinates.length < 2) {
                gameIO.showMessage("You entered too few arguments. Enter exactly two integers separated by comma.");
                continue;
            } else if (coordinates.length > 2) {
                gameIO.showMessage("You entered too many arguments. Enter exactly two integers separated by comma.");
                continue;
            }

            try {
                x = Integer.parseInt(coordinates[0].trim()) - 1;
            }
            catch (NumberFormatException e) {
                gameIO.showMessage("Input " + coordinates[0] + " is not valid. Enter a valid integer");
                continue;
            }

            try {
                y = Integer.parseInt(coordinates[1].trim()) - 1;
            }
            catch (NumberFormatException e) {
                gameIO.showMessage("Input " + coordinates[1] + " is not valid. Enter a valid integer");
                continue;
            }
            break;
        }

        return new int[] {x,y};
    }

}




