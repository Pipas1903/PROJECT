package com.arcade.games.rockPaperScissors;

import com.arcade.common.Constants;
import com.arcade.common.GamesInfo;
import com.arcade.common.Messages;
import com.arcade.common.Utils;
import com.arcade.player.Player;
import com.arcade.leaderboard.*;
import com.arcade.player.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RockPaperScissors {
    private final GamesInfo game = GamesInfo.ROCK_PAPER_SCISSORS;
    private Player playerOne;
    private Player playerTwo;

    private Socket clientSocket;

    PrintWriter out;
    BufferedReader in;

    int rounds = 10;
    int points = 21;
    int pcPoints = 0;
    Moves moves;

    private String winner = "";
    private String lastWinner = "";

    public RockPaperScissors(Player player) {
        this.playerOne = player;
    }

    public RockPaperScissors(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void startGame1Players() throws IOException {
        playerOne.setCurrentScore(0);

        System.out.println(Constants.ANSI_YELLOW + Messages.WELCOME_TO_ROCK_PAPER_SCISSORS + Constants.ANSI_RESET);
        System.out.println();

        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();

        for (int i = 1; i <= rounds; i++) {
            System.out.println(Constants.ANSI_BLUE_BACKGROUND + Constants.ANSI_BLACK + Messages.ROUND + i + Constants.ANSI_RESET);
            System.out.println();

            System.out.println(playerOne.getNickname() + Messages.ENTER_YOUR_MOVE);
            System.out.println("    * 1 rock\n    * 2 paper\n    * 3 scissors ");

            String chosenMove = Utils.scanString.nextLine();
            while (isMoveIlegal(chosenMove)) {
                System.out.println(Messages.ILLEGAL_MOVE);
                chosenMove = Utils.scanString.nextLine();
            }

            Moves playerMove = null;

            switch (chosenMove) {
                case "1":
                    playerMove = Moves.ROCK;
                    break;
                case "2":
                    playerMove = Moves.PAPER;
                    break;
                case "3":
                    playerMove = Moves.SCISSORS;
                    break;
            }

            Moves pcMove = Moves.getRandomMove();

            winner = playerMove.play(pcMove, playerOne.getNickname());

            if (winner.equals(Messages.PC)) {
                pcPoints += points;
            }

            System.out.println(playerOne.getNickname() + ": " + playerMove + " vs " + Messages.PC + ": " + pcMove);

            if (winner.equals(playerOne.getNickname())) {
                assignPoints(playerOne);
            }
            System.out.println(Messages.ANNOUNCE_WINNER + winner);
            System.out.println();
        }
        System.out.println(Constants.ANSI_PURPLE + playerOne.getNickname() + " -> " + playerOne.getCurrentScore() + Constants.ANSI_RESET);
        System.out.println(Constants.ANSI_PURPLE + Messages.PC + " -> " + pcPoints + Constants.ANSI_RESET);
        System.out.println();
        System.out.println(Messages.ANNOUNCE_WINNER + (playerOne.getCurrentScore() > pcPoints ? playerOne.getNickname() : Messages.PC));

        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(), playerOne.getCurrentScore(), game);
        LeaderboardManager.manageScores(game, playerOne.getNickname(), playerOne.getCurrentScore());

        System.out.println(Messages.GREAT_GAME);
    }

    public void startGame2Players() throws IOException {
        playerOne.setCurrentScore(0);
        playerTwo.setCurrentScore(0);
        System.out.println(Constants.ANSI_YELLOW + Messages.WELCOME_TO_ROCK_PAPER_SCISSORS + Constants.ANSI_RESET);
        System.out.println();
        System.out.println(Constants.ANSI_RED + Messages.REMEMBER + Constants.ANSI_RESET);

        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();

        connect();
        for (int i = 1; i <= rounds; i++) {
            System.out.println(Constants.ANSI_BLUE_BACKGROUND + Constants.ANSI_BLACK + Messages.ROUND + i + Constants.ANSI_RESET);
            System.out.println();
            out.println(Constants.ANSI_BLUE_BACKGROUND + Constants.ANSI_BLACK + Messages.ROUND + i + Constants.ANSI_RESET);
            out.println(Messages.WAITING_FOR_PLAYER_1 + "\n");

            System.out.println(playerOne.getNickname() + Messages.ENTER_YOUR_MOVE);
            String player1Move = getPlayer1Move();
            System.out.println();
            System.out.println(Messages.WAITING_FOR_PLAYER_2);

            String player2Move = receiveMoves();
            System.out.println();
            System.out.println(playerOne.getNickname() + ": " + player1Move + " vs " + playerTwo.getNickname() + ": " + player2Move);
            System.out.println();
            rules(player1Move, player2Move);
            System.out.println();
            comunicate(player1Move, player2Move);

        }
        System.out.println();

        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(), playerOne.getCurrentScore(), game);
        PlayerManager.addScoreToPlayerFile(playerTwo.getNickname(), playerTwo.getCurrentScore(), game);
        LeaderboardManager.manageScores(game, playerOne.getNickname(), playerOne.getCurrentScore());
        LeaderboardManager.manageScores(game, playerTwo.getNickname(), playerTwo.getCurrentScore());

        printEndGameStats();
    }

    private void printEndGameStats() {
        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();
        System.out.println(Constants.ANSI_PURPLE + playerOne.getNickname() + " -> " + playerOne.getCurrentScore() + Constants.ANSI_RESET);
        System.out.println(Constants.ANSI_BLUE + playerTwo.getNickname() + " -> " + playerTwo.getCurrentScore() + Constants.ANSI_RESET);
        System.out.println();
        System.out.println(Messages.ANNOUNCE_WINNER + (playerOne.getCurrentScore() > playerTwo.getCurrentScore() ? playerOne.getNickname() : playerTwo.getNickname()));
        System.out.println();
        out.println(Constants.ANSI_PURPLE + playerOne.getNickname() + " -> " + playerOne.getCurrentScore() + Constants.ANSI_RESET);
        out.println(Constants.ANSI_BLUE + playerTwo.getNickname() + " -> " + playerTwo.getCurrentScore() + Constants.ANSI_RESET);
        out.println(Messages.ANNOUNCE_WINNER + (playerOne.getCurrentScore() > playerTwo.getCurrentScore() ? playerOne.getNickname() : playerTwo.getNickname()));
        out.println(Messages.GREAT_GAME);
        System.out.println(Messages.GREAT_GAME);
        System.out.println();
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

    private void rules(String playerChose, String player2Chose) {
        if (player2Chose.equals(playerChose)) {
            System.out.println("it's a tie");
            winner = "tie";
            lastWinner = "";
            return;
        }

        if ((player2Chose.equalsIgnoreCase("ROCK") && playerChose.equalsIgnoreCase("PAPER")) || player2Chose.equalsIgnoreCase("PAPER") && playerChose.equalsIgnoreCase("SCISSORS") || player2Chose.equalsIgnoreCase("SCISSORS") && playerChose.equalsIgnoreCase("ROCK")) {
            System.out.println(Messages.ANNOUNCE_WINNER + playerOne.getNickname());
            winner = playerOne.getNickname();
            assignPoints(playerOne);
            return;
        }

        System.out.println(Messages.ANNOUNCE_WINNER + playerTwo.getNickname());
        winner = playerTwo.getNickname();
        assignPoints(playerTwo);
        playerTwo.setCurrentScore(playerTwo.getCurrentScore() + points);
    }

    private void connect() throws IOException {

        ServerSocket serverSocket = new ServerSocket(92);
        System.out.println(Messages.WAITING);
        clientSocket = serverSocket.accept();
        System.out.println(Messages.CONNECTED);
        System.out.println();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(Constants.ANSI_YELLOW + Messages.WELCOME_TO_ROCK_PAPER_SCISSORS + Constants.ANSI_RESET + "\n");
    }

    private String receiveMoves() throws IOException {
        out.println(Messages.ENTER_YOUR_MOVE);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String player2Move = in.readLine();
        while (isInputInvalid(player2Move)) {
            out.println(Messages.INVALID_INPUT);
            out.println(Messages.TRY_AGAIN + "\n");
            player2Move = in.readLine();
        }
        return player2Move;
    }

    private void comunicate(String player1move, String player2Move) {
        out.println("\n");
        out.println(playerOne.getNickname() + ": " + player1move + " vs " + playerTwo.getNickname() + ": " + player2Move);
        out.println("The winner of this rounds is: " + winner);
        out.println("\n");
    }


    private boolean isInputInvalid(String playerMove) {
        return !playerMove.equalsIgnoreCase(String.valueOf(Moves.PAPER)) && !playerMove.equalsIgnoreCase(String.valueOf(Moves.ROCK)) && !playerMove.equalsIgnoreCase(String.valueOf(Moves.SCISSORS));
    }

    private void assignPoints(Player player) {
        if (lastWinner.isEmpty() || (!lastWinner.equals(player.getNickname()) && winner.equals(player.getNickname()))) {
            player.setConsecutiveRoundsWon(0);
            player.setCurrentScore(player.getCurrentScore() + points);
        }
        if (lastWinner.equals(player.getNickname()) && winner.equals(player.getNickname())) {
            player.setConsecutiveRoundsWon(player.getConsecutiveRoundsWon() + 1);
            player.setCurrentScore(player.getCurrentScore() + player.getConsecutiveRoundsWon() * points * 2);
        }
    }

    private boolean isMoveIlegal(String chosenMove) {
        return !(chosenMove.matches("[1-3]"));
    }
}

