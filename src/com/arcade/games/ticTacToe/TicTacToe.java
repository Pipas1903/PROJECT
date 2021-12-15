package com.arcade.games.ticTacToe;

import com.arcade.common.*;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.nio.file.Files;

public class TicTacToe {
    private final GamesInfo game = GamesInfo.TIC_TAC_TOE;
    private char[][] board = new char[3][3];
    final int RADIX = 10;
    private String winner = "";
    private Player playerOne;
    private Player playerTwo;
    private int rounds = 10;
    private String lastWinner = "";
    private int points = 5;

    public TicTacToe(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    private void chooseSymbol() {
        System.out.println(playerOne.getNickname() + " - " + Messages.CHOOSE_SYMBOL + Messages.X + " or " + Messages.O);
        do {
            String chosen = Utils.scanString.nextLine();
            if (!chosen.equalsIgnoreCase(String.valueOf(Messages.X)) && !chosen.equalsIgnoreCase(String.valueOf(Messages.O))) {
                System.out.println(Messages.INVALID_INPUT);
                System.out.println(Messages.TRY_AGAIN);
            } else if (chosen.equalsIgnoreCase(String.valueOf(Messages.O))) {
                playerOne.setSymbol(Messages.O);
                playerTwo.setSymbol(Messages.X);
                return;
            } else {
                playerOne.setSymbol(Messages.X);
                playerTwo.setSymbol(Messages.O);
                return;
            }

        } while (true);
    }

    private void assignPoints(Player player) {
        if (lastWinner.isEmpty() || (!lastWinner.equals(player.getNickname()) && winner.equals(player.getNickname()))) {
            player.setConsecutiveRoundsWon(0);
            player.setCurrentScore(player.getCurrentScore() + points);
        }
        if (lastWinner.equals(player.getNickname()) && winner.equals(player.getNickname())) {
            player.setConsecutiveRoundsWon(player.getConsecutiveRoundsWon() + 1);
            player.setCurrentScore(player.getCurrentScore() + player.getConsecutiveRoundsWon() * points);
        }
    }

    private void startRound() {
        do {
            turn(playerOne);
            hasPlayerWon(playerOne);
            turn(playerTwo);
            hasPlayerWon(playerTwo);
        } while (winner.isEmpty());
        board = new char[3][3];
        stop();
    }

    public void startGame() {
        System.out.println(Messages.ARE_YOU_READY + Constants.TIC_TAC_TOE);
        chooseSymbol();
        for (int i = 0; i < rounds; i++) {
            System.out.println(Messages.ROUND + (i + 1));
            startRound();
            System.out.println();
            System.out.println();
            System.out.println(playerOne.getCurrentScore());
            System.out.println(playerTwo.getCurrentScore());
        }
        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(),playerOne.getCurrentScore(),game);
        PlayerManager.addScoreToPlayerFile(playerTwo.getNickname(),playerTwo.getCurrentScore(),game);
        System.out.println(Messages.GREAT_GAME);
    }

    private void draw() {

        int coordinates = 0;
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                coordinates += 1;
                if (board[i][j] != Messages.X && board[i][j] != Messages.O) {
                    board[i][j] = Character.forDigit(coordinates, RADIX);
                    System.out.print(coordinates);
                } else {
                    System.out.print(board[i][j]);
                }
                if (j != board.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("-+-+-");
        }
    }

    private int playerMakeMove() {
        System.out.print(Messages.CHOOSE_COORDINATES);
        int choosen = Utils.scanInt.nextInt();
        do {
            if (isMoveIlegal(choosen)) {
                System.out.println(Messages.ILLEGAL_MOVE);
                choosen = Utils.scanInt.nextInt();
            }
            if (isTileOccupied(choosen)) {
                System.out.println(Messages.OCCUPIED_TILE);
                choosen = Utils.scanInt.nextInt();
            }
        } while (isTileOccupied(choosen) || isMoveIlegal(choosen));

        return choosen;
    }

    private void placePieceInBoard(int coordinates, Player player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                if (board[i][j] == Character.forDigit(coordinates, RADIX)) {
                    board[i][j] = player.getSymbol();
                    return;
                }
        }
    }

    private void turn(Player player) {
        if (!winner.isEmpty()) {
            return;
        }

        if (freeTiles() == 0) {
            winner = Messages.TIE;
            return;
        }
        draw();

        System.out.println("It's " + player.getNickname() + " turn.");
        placePieceInBoard(playerMakeMove(), player);
    }


    private int freeTiles() {
        int freeTiles = 9;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Messages.O || board[i][j] == Messages.X) {
                    freeTiles--;
                }
            }
        }
        return freeTiles;
    }

    private boolean isTileOccupied(int coordinates) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Character.forDigit(coordinates, RADIX)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isMoveIlegal(int coordinates) {
        if (!(String.valueOf(coordinates).matches("[1-9]"))) {
            return true;
        }
        return false;
    }

    private void hasPlayerWon(Player player) {

        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == player.getSymbol() && board[1][i] == player.getSymbol() && board[2][i] == player.getSymbol()) {
                winner = player.getNickname();
            } else if (board[i][0] == player.getSymbol() && board[i][1] == player.getSymbol() && board[i][2] == player.getSymbol()) {
                winner = player.getNickname();
            }
        }
        if (board[0][0] == player.getSymbol() && board[1][1] == player.getSymbol() && board[2][2] == player.getSymbol() || (board[0][2] == player.getSymbol() && board[2][0] == player.getSymbol() && board[1][1] == player.getSymbol())) {
            winner = player.getNickname();
        }
        lastWinner = player.getNickname();
    }

    private void stop() {
        draw();
        if (winner.equals(Messages.TIE)) {
            System.out.println(Messages.TIE_ANNOUNCE);
        } else if (winner.equals(playerOne.getNickname())) {
            System.out.println(Messages.ANNOUNCE_WINNER + playerOne.getNickname());
            assignPoints(playerOne);
        } else {
            System.out.println(Messages.ANNOUNCE_WINNER + playerTwo.getNickname());
            assignPoints(playerTwo);
        }
    }
}
