package org.example.gameio;

import org.example.board.Board;
import org.example.board.CellState;
import org.example.network.ClientDisconnectedException;
import org.example.player.PlayerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkGameIO extends BaseGameIO{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public NetworkGameIO(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    void sendMessage(String message){
        out.println(message);
    }

    @Override
    String receiveLine(){
        sendMessage("INPUT_REQUIRED");
        String s;
        try {
            s = in.readLine();
            if (s != null){
                s = s.trim();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (s == null){
            throw new ClientDisconnectedException("Player disconnected");
        }
        return s;
    }


    @Override
    public PlayerType getPlayerType(String playerNum) {
        return PlayerType.HUMAN;
    }

    @Override
    public void showBoard(Board board) {
        CellState[][] boardArray = board.board;
        for (int i = 0; i < board.boardSize; i++) {
            for (int j = 0; j < board.boardSize; j++) {
                out.print(boardArray[i][j].getSymbol());
            }
            out.println();
        }
    }

}
