package com.arcade.games.ticTacToe;

import com.arcade.common.*;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.io.IOException;
import java.util.LinkedList;

public class TicTacToe {
    private final GamesInfo game = GamesInfo.TIC_TAC_TOE;

    private String[][] board = new String[3][3];

    private String winner = "";
    private Player playerOne;
    private Player playerTwo;
    private String lastWinner = "";
    private String aiSymbol;

    private int rounds = 3;
    private int points = 21;


    // FOR 2 PLAYERS
    public TicTacToe(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }


    private void chooseSymbol2Player() {
        System.out.println(playerOne.getNickname() + " - " + Messages.CHOOSE_SYMBOL + Constants.X + " or " + Constants.O);
        do {
            String chosen = Utils.scanString.nextLine();
            if (!chosen.equalsIgnoreCase(Constants.X) && !chosen.equalsIgnoreCase(Constants.O)) {
                System.out.println(Messages.INVALID_INPUT);
                System.out.println(Messages.TRY_AGAIN);
            } else if (chosen.equalsIgnoreCase(Constants.O)) {
                playerOne.setSymbol(Constants.O);
                playerTwo.setSymbol(Constants.X);
                return;
            } else {
                playerOne.setSymbol(Constants.X);
                playerTwo.setSymbol(Constants.O);
                return;
            }

        } while (true);
    }


    public void startGame2Players() throws IOException {
        System.out.println(Messages.ARE_YOU_READY + Constants.TIC_TAC_TOE);
        chooseSymbol2Player();
        for (int i = 0; i < rounds; i++) {

            System.out.println(Messages.ROUND + (i + 1));
            startRound2Players();
        }
        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(), playerOne.getCurrentScore(), game);
        PlayerManager.addScoreToPlayerFile(playerTwo.getNickname(), playerTwo.getCurrentScore(), game);
        LeaderboardManager.manageScores(game, playerOne.getNickname(), playerOne.getCurrentScore());
        LeaderboardManager.manageScores(game, playerTwo.getNickname(), playerTwo.getCurrentScore());
        System.out.println(Messages.GREAT_GAME);
    }


    private void startRound2Players() {
        do {
            turn(playerOne);
            hasPlayerWon(playerOne);
            turn(playerTwo);
            hasPlayerWon(playerTwo);
        } while (winner.isEmpty());
        stop();
        board = new String[3][3];
        winner = "";
    }


    private String playerMakeMove() {
        System.out.print(Messages.CHOOSE_COORDINATES);
        String chosen = Utils.scanString.nextLine();
        do {
            if (isMoveIlegal(chosen)) {
                System.out.println(Messages.ILLEGAL_MOVE);
                chosen = Utils.scanString.nextLine();
            }
            if (isTileOccupied(chosen)) {
                System.out.println(Messages.OCCUPIED_TILE);
                chosen = Utils.scanString.nextLine();
            }

        } while (isTileOccupied(chosen) || isMoveIlegal(chosen));

        return chosen;
    }


    private void placePieceInBoard(String coordinates, Player player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                if (board[i][j].equals(coordinates)) {
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
                if (board[i][j] != null && (board[i][j].equals(Constants.O) || board[i][j].equals(Constants.X))) {
                    freeTiles--;
                }
            }

        }
        return freeTiles;
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

    // COMMON
    private void draw() {

        int coordinates = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                coordinates += 1;
                if (board[i][j] == null || !board[i][j].equals(Constants.X) && !board[i][j].equals(Constants.O)) {
                    board[i][j] = String.valueOf(coordinates);
                    System.out.print(coordinates);
                } else {
                    System.out.print(board[i][j].equals(Constants.X) ? (Constants.ANSI_GREEN + board[i][j] + Constants.ANSI_RESET) : (Constants.ANSI_RED + board[i][j] + Constants.ANSI_RESET));
                }
                if (j != board.length - 1) {
                    System.out.print(" | ");
                }

            }
            System.out.println();
            if (i < 2) {
                System.out.println("--+---+--");
            }
        }
        System.out.println();
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


    private boolean isTileOccupied(String coordinates) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(coordinates)) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean isMoveIlegal(String coordinates) {
        return !(coordinates.matches("[1-9]"));
    }


    // FOR 1 PLAYER
    public TicTacToe(Player playerOne) {
        this.playerOne = playerOne;
    }

    private void chooseSymbol1Player() {
        System.out.println(playerOne.getNickname() + " - " + Messages.CHOOSE_SYMBOL + Constants.X + " or " + Constants.O);
        do {
            String chosen = Utils.scanString.nextLine();
            if (!chosen.equalsIgnoreCase(Constants.X) && !chosen.equalsIgnoreCase(Constants.O)) {
                System.out.println(Messages.INVALID_INPUT);
                System.out.println(Messages.TRY_AGAIN);
            } else if (chosen.equalsIgnoreCase(Constants.O)) {
                playerOne.setSymbol(Constants.O);
                aiSymbol = Constants.X;
                return;
            } else {
                playerOne.setSymbol(Constants.X);
                aiSymbol = Constants.O;
                return;
            }

        } while (true);
    }


    private LinkedList<String> aiFindPossibleMoves() {

        LinkedList<String> freeTiles = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!isMoveIlegal(board[i][j])) freeTiles.add(board[i][j]);
            }
        }
        return freeTiles;
    }

    private static String pcChooseTile(LinkedList<String> allFreeTiles) {
        if (allFreeTiles.size() == 0) return null;

        int randomNumber = (int) Math.floor(Math.random() * (allFreeTiles.size()));
        return allFreeTiles.get(randomNumber);
    }

    private void pcPlacePieceInBoard(String coordinates) {
        if (coordinates == null) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                if (board[i][j].equals(coordinates)) {
                    board[i][j] = aiSymbol;
                    return;
                }
        }
    }

    private void startRound1Player() {
        do {
            clearScreen();
            turn(playerOne);
            hasPlayerWon(playerOne);
            pcPlacePieceInBoard(pcChooseTile(aiFindPossibleMoves()));
            hasPCWon();
        } while (winner.isEmpty());
        end();
        board = new String[3][3];
        winner = "";
    }

    public void startGame1Players() throws IOException {
        System.out.println(Messages.ARE_YOU_READY + Constants.TIC_TAC_TOE);
        chooseSymbol1Player();
        for (int i = 0; i < rounds; i++) {
            System.out.println(Messages.ROUND + (i + 1));
            startRound1Player();

        }
        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(), playerOne.getCurrentScore(), game);
        LeaderboardManager.manageScores(game, playerOne.getNickname(), playerOne.getCurrentScore());
        System.out.println(Messages.GREAT_GAME);
    }

    private void hasPCWon() {

        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == aiSymbol && board[1][i] == aiSymbol && board[2][i] == aiSymbol) {
                winner = Messages.PC;
            } else if (board[i][0] == aiSymbol && board[i][1] == aiSymbol && board[i][2] == aiSymbol) {
                winner = Messages.PC;
            }
        }
        if (board[0][0] == aiSymbol && board[1][1] == aiSymbol && board[2][2] == aiSymbol || (board[0][2] == aiSymbol && board[2][0] == aiSymbol && board[1][1] == aiSymbol)) {
            winner = Messages.PC;
        }
        lastWinner = Messages.PC;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void end() {
        draw();
        if (winner.equals(Messages.TIE)) {
            System.out.println(Messages.TIE_ANNOUNCE);
        } else if (winner.equals(playerOne.getNickname())) {
            System.out.println(Messages.ANNOUNCE_WINNER + playerOne.getNickname());
            assignPoints(playerOne);
        } else {
            System.out.println(Messages.ANNOUNCE_WINNER + Messages.PC);
        }
    }

}
