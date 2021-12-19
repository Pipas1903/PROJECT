package com.arcade.games.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String playerMove;
    private Socket clientSocket;

    public void connect() throws IOException {

        ServerSocket serverSocket = new ServerSocket(92);
        System.out.println("waiting for someone to connect");
        clientSocket = serverSocket.accept(); // blocking method!
        System.out.println("connected");
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("Welcome to Rock Paper Scissors!\n");
        receiveMoves(out);
        serverSocket.close();
    }

    public String receiveMoves(PrintWriter writer) throws IOException {
        while (clientSocket.isConnected()) {
            writer.println(" What's your move?");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            playerMove = in.readLine();
            while (isInputInvalid(playerMove)) {
                writer.println("invalid move, please try again");
                playerMove = in.readLine();
            }
            return playerMove;
        }
        return null;
    }

    private boolean isInputInvalid(String playerMove) {
        return !playerMove.equalsIgnoreCase(String.valueOf(Moves.PAPER)) && !playerMove.equalsIgnoreCase(String.valueOf(Moves.ROCK)) && !playerMove.equalsIgnoreCase(String.valueOf(Moves.SCISSORS));
    }


    public String getPlayerMove() {
        return playerMove;
    }
}
