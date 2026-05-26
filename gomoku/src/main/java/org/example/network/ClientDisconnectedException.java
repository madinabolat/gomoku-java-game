package org.example.network;

public class ClientDisconnectedException extends RuntimeException{
    public ClientDisconnectedException(String message) {
        super(message);
    }
}
