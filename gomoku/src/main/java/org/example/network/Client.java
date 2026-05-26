package org.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static int PORT = 1111;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true){

                String response = in.readLine();
                if (response == null){
                    break;
                }
                System.out.println(response);
                if (response.equals("INPUT_REQUIRED")){
                    out.println(userInput.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
