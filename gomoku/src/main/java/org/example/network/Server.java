package org.example.network;

import org.example.game.Game;
import org.example.game.GameSetup;
import org.example.gameio.ConsoleGameIO;
import org.example.gameio.GameIO;
import org.example.gameio.NetworkGameIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1111;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server is running on port " + PORT);
            Socket clientOne = server.accept();
            Socket clientTwo = server.accept();
            GameIO gameIOPlayerOne = new NetworkGameIO(clientOne);
            GameIO gameIOPlayerTwo = new NetworkGameIO(clientTwo);
            GameSetup gameSetup = new GameSetup(gameIOPlayerOne, gameIOPlayerTwo);
            gameSetup.initializeGame();
            Game game = new Game(gameSetup);
            game.playGame();
            server.close();
            clientOne.close();
            clientTwo.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClientDisconnectedException e){
            System.out.println(e.getMessage());
        }
    }
}
