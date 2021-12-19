package com.arcade.games.rockPaperScissors;

import com.arcade.common.Constants;
import com.arcade.common.Messages;
import com.arcade.common.Utils;
import com.arcade.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RockPaperScissors {
    private Player playerOne;
    private Player playerTwo;

    private String playerMove;

    private Socket clientSocket;

    PrintWriter out;
    BufferedReader in;

    int rounds = 10;
    int points = 21;
    Moves moves;
    String winner;

    public RockPaperScissors(Player player) {
        this.playerOne = player;
    }

    public RockPaperScissors(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void startGame1Players() {


    }

    public void startGame2Players() throws IOException {
        System.out.println(Messages.WELCOME_TO_ROCK_PAPER_SCISSORS);
        System.out.println();
        System.out.println(Constants.ANSI_RED + Messages.REMEMBER + Constants.ANSI_RESET);
        System.out.println();
        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();
        int counter = 1;
        connect();
        while (counter < rounds) {
            System.out.println(playerOne.getNickname() + Messages.ENTER_YOUR_MOVE);
            String player1Move = getPlayer1Move();
            System.out.println(Messages.WAITING_FOR_PLAYER_2);
            String player2Move = receiveMoves();
            rules(player1Move, player2Move);
            comunicate();
        }
    }

    private String getPlayer1Move() {
        String player1Move = Utils.scanString.nextLine();

        while (isInputInvalid(player1Move)) {
            System.out.println(Messages.INVALID_INPUT);
            System.out.println(Messages.TRY_AGAIN);
            player1Move = Utils.scanString.nextLine();
        }
        return player1Move;
    }

    public void rules(String playerChose, String player2Chose) {
        if (player2Chose.equals(playerChose)) {
            System.out.println("it's a tie");
            winner = "tie";
            return;
        }

        if (player2Chose.equals("ROCK") && playerChose.equals("PAPER") || player2Chose.equals("PAPER") && playerChose.equals("SCISSORS") || player2Chose.equals("SCISSORS") && playerChose.equals("ROCK")) {
            System.out.println(playerOne.getNickname() + " won this round!");
            winner = playerTwo.getNickname();
            playerOne.setCurrentScore(playerOne.getCurrentScore() + points);
            return;
        }
        System.out.println("Player 1 won this round!");
        winner = playerOne.getNickname();
        playerTwo.setCurrentScore(playerTwo.getCurrentScore() + points);
    }

    public void connect() throws IOException {

        ServerSocket serverSocket = new ServerSocket(92);
        System.out.println(Messages.WAITING);
        clientSocket = serverSocket.accept();
        System.out.println(Messages.CONNECTED);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(Messages.WELCOME_TO_ROCK_PAPER_SCISSORS + "\n");
        out.println(Messages.WAITING_FOR_PLAYER_1 + "\n");
    }

    public String receiveMoves() throws IOException {
        out.println(Messages.ENTER_YOUR_MOVE);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        playerMove = in.readLine();
        while (isInputInvalid(playerMove)) {
            out.println(Messages.INVALID_INPUT);
            out.println(Messages.TRY_AGAIN + "\n");
            playerMove = in.readLine();
        }
        return playerMove;
    }

    public void comunicate() {
        out.println("The winner of this rounds is ....");
        out.print(winner);
    }


    private boolean isInputInvalid(String playerMove) {
        return !playerMove.equalsIgnoreCase(String.valueOf(Moves.PAPER)) && !playerMove.equalsIgnoreCase(String.valueOf(Moves.ROCK)) && !playerMove.equalsIgnoreCase(String.valueOf(Moves.SCISSORS));
    }


    public String getPlayerMove() {
        return playerMove;
    }
}

