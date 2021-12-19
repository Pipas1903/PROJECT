package com.arcade.games.fourInLine;

import com.arcade.common.Constants;
import com.arcade.common.GamesInfo;
import com.arcade.common.Messages;
import com.arcade.common.Utils;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.io.IOException;
import java.util.LinkedList;

public class FourInLine {
    private final GamesInfo game = GamesInfo.FOUR_IN_LINE;

    private Player playerOne;
    private Player playerTwo;
    private String winner = "";
    private String lastWinner = "";
    private String aiSymbol;
    private int pcScore;

    private int points = 21;
    private int rounds = 1;

    private String[][] board = new String[6][7];

    public FourInLine(Player player) {
        this.playerOne = player;
    }

    public FourInLine(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    // 1 Player Vs Ai
    public void startGame1Players() throws IOException {
        System.out.println(Messages.ARE_YOU_READY + Constants.FOUR_IN_LINE);
        chooseSymbol1Player();
        for (int i = 0; i < rounds; i++) {
            System.out.println(Messages.ROUND + (i + 1));
            startRound1Player();
        }
        System.out.println();
        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(), playerOne.getCurrentScore(), game);
        LeaderboardManager.manageScores(game, playerOne.getNickname(), playerOne.getCurrentScore());
        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();
        System.out.println(Constants.ANSI_PURPLE + playerOne.getNickname() + " -> " + playerOne.getCurrentScore() + Constants.ANSI_RESET);
        System.out.println(Constants.ANSI_PURPLE + Messages.PC + " -> " + pcScore + Constants.ANSI_RESET);
        System.out.println();
        System.out.println(Messages.ANNOUNCE_WINNER + (playerOne.getCurrentScore() > pcScore ? playerOne.getNickname() : Messages.PC));
        System.out.println();
        System.out.println(Messages.GREAT_GAME);
        System.out.println();
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
        board = new String[6][7];
        winner = "";
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

    // Player 1 Vs Player 2
    public void startGame2Players() throws IOException {
        System.out.println(Messages.ARE_YOU_READY + Constants.FOUR_IN_LINE);
        chooseSymbol2Player();
        for (int i = 0; i < rounds; i++) {

            System.out.println(Messages.ROUND + (i + 1));
            startRound2Players();
        }
        System.out.println();
        PlayerManager.addScoreToPlayerFile(playerOne.getNickname(), playerOne.getCurrentScore(), game);
        PlayerManager.addScoreToPlayerFile(playerTwo.getNickname(), playerTwo.getCurrentScore(), game);
        LeaderboardManager.manageScores(game, playerOne.getNickname(), playerOne.getCurrentScore());
        LeaderboardManager.manageScores(game, playerTwo.getNickname(), playerTwo.getCurrentScore());
        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();
        System.out.println(Constants.ANSI_PURPLE + playerOne.getNickname() + " -> " + playerOne.getCurrentScore() + Constants.ANSI_RESET);
        System.out.println(Constants.ANSI_BLUE + playerTwo.getNickname() + " -> " + playerTwo.getCurrentScore() + Constants.ANSI_RESET);
        System.out.println();
        System.out.println(Messages.ANNOUNCE_WINNER + (playerOne.getCurrentScore() > playerTwo.getCurrentScore() ? playerOne.getNickname() : playerTwo.getNickname()));
        System.out.println();
        System.out.println(Messages.GREAT_GAME);
        System.out.println();
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

    //Ai
    private LinkedList<Integer> aiFindPossibleMoves() {

        LinkedList<Integer> freeColumns = new LinkedList<>();
        for (int i = 0; i < board[0].length; i++) {
            if (!isColumnFull(i)) {
                freeColumns.add(i);
            }
        }
        return freeColumns;
    }

    private Integer pcChooseTile(LinkedList<Integer> columns) {
        if (columns.size() == 0) return null;

        int randomNumber = (int) Math.floor(Math.random() * (columns.size()));
        return columns.get(randomNumber);
    }

    private void pcPlacePieceInBoard(int coordinates) {
        int cordNumber = coordinates;
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][cordNumber] == null) {
                board[i][cordNumber] = aiSymbol;
                return;
            }
        }
    }

    //Commons
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

    private void placePieceInBoard(String coordinates, Player player) {
        int cordNumber = Integer.parseInt(coordinates) - 1;
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][cordNumber] == null) {
                board[i][cordNumber] = player.getSymbol();
                return;
            }
        }
    }

    private int freeTiles() {
        int freeTiles = board.length * board[0].length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && (board[i][j].equals(Constants.O) || board[i][j].equals(Constants.X))) {
                    freeTiles--;
                }
            }
        }

        return freeTiles;
    }

    private String playerMakeMove() {
        System.out.print(Messages.CHOOSE_COORDINATES);
        String chosen = Utils.scanString.nextLine();
        do {
            if (isMoveIllegal(chosen)) {
                System.out.println(Messages.ILLEGAL_MOVE);
                chosen = Utils.scanString.nextLine();
            }
            if (isColumnFull(chosen)) {
                System.out.println(Messages.OCCUPIED_TILE);
                chosen = Utils.scanString.nextLine();
            }

        } while (isColumnFull(chosen) || isMoveIllegal(chosen));

        return chosen;
    }

    private boolean isMoveIllegal(String coordinates) {
        return !(coordinates.matches("[1-7]"));
    }

    private boolean isColumnFull(String coordinate) {
        return !(board[0][Integer.parseInt(coordinate) - 1] == null);
    }

    private boolean isColumnFull(int coordinate) {
        return !(board[0][coordinate] == null);
    }

    private void draw() {

        //Column Numbers
        for (int i = 1; i < board[0].length + 1; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        //Board's body
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == null || !board[i][j].equals(Constants.X) && !board[i][j].equals(Constants.O)) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j].equals(Constants.X) ? (Constants.ANSI_GREEN + board[i][j] + Constants.ANSI_RESET) : (Constants.ANSI_RED + board[i][j] + Constants.ANSI_RESET));
                }
                if (j != board.length) {
                    System.out.print(" | ");
                }

            }
            System.out.println();
            if (i < board.length - 1) {
                for (int j = 0; j < board[i].length - 1; j++) {
                    System.out.print("--+-");
                }

                System.out.println("-");
            }
        }
        System.out.println();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    private void end() {
        draw();
        if (winner.equals(Messages.TIE)) {
            System.out.println(Messages.TIE_ANNOUNCE);
        } else if (winner.equals(playerOne.getNickname())) {
            System.out.println(Messages.ANNOUNCE_WINNER + playerOne.getNickname());
            assignPoints(playerOne);
        } else {
            System.out.println(Messages.ANNOUNCE_WINNER + Messages.PC);
            pcScore += points;
        }
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

    private void hasPlayerWon(Player player) {
        boolean hasWon = false;

        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = board[row].length - 1; col >= 0; col--) {

                //check for 4 up
                if (!hasWon && row - 3 >= 0) {
                    if (board[row][col] == player.getSymbol() &&
                            board[row - 1][col] == player.getSymbol() &&
                            board[row - 2][col] == player.getSymbol() &&
                            board[row - 3][col] == player.getSymbol()) {
                        hasWon = true;

                    }
                }
                //check for 4 left
                if (!hasWon & col - 3 >= 0) {
                    if (board[row][col] == player.getSymbol() &&
                            board[row][col - 1] == player.getSymbol() &&
                            board[row][col - 2] == player.getSymbol() &&
                            board[row][col - 3] == player.getSymbol()) {
                        hasWon = true;
                    }
                }
                //check for 4 right diagonal
                if (!hasWon && row - 3 >= 0 && col + 3 < board[row].length)
                    if (board[row][col] == player.getSymbol() &&
                            board[row - 1][col + 1] == player.getSymbol() &&
                            board[row - 2][col + 2] == player.getSymbol() &&
                            board[row - 3][col + 3] == player.getSymbol()) {
                        hasWon = true;
                    }
                //check for 4 left diagonal
                if (!hasWon && row - 3 >= 0 && col - 3 >= 0)
                    if (board[row][col] == player.getSymbol() &&
                            board[row - 1][col - 1] == player.getSymbol() &&
                            board[row - 2][col - 2] == player.getSymbol() &&
                            board[row - 3][col - 3] == player.getSymbol()) {
                        hasWon = true;
                    }
            }
        }
        if (hasWon) {
            winner = player.getNickname();
            lastWinner = player.getNickname();
        }

    }

    private void hasPCWon() {
        boolean hasWon = false;

        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = board[row].length - 1; col >= 0; col--) {

                //check for 4 up
                if (!hasWon & row - 3 >= 0) {
                    if (board[row][col] == aiSymbol &&
                            board[row - 1][col] == aiSymbol &&
                            board[row - 2][col] == aiSymbol &&
                            board[row - 3][col] == aiSymbol) {
                        hasWon = true;
                    }
                }

                //check for 4 left
                if (!hasWon && col - 3 >= 0)
                    if (board[row][col] == aiSymbol &&
                            board[row][col - 1] == aiSymbol &&
                            board[row][col - 2] == aiSymbol &&
                            board[row][col - 3] == aiSymbol) {
                        hasWon = true;

                    }

                //check for 4 right diagonal
                if (!hasWon && row - 3 >= 0 && col + 3 < board[row].length)
                    if (board[row][col] == aiSymbol &&
                            board[row - 1][col + 1] == aiSymbol &&
                            board[row - 2][col + 2] == aiSymbol &&
                            board[row - 3][col + 3] == aiSymbol) {
                        hasWon = true;
                    }

                //check for 4 left diagonal
                if (!hasWon && row - 3 >= 0 && col - 3 >= 0)
                    if (board[row][col] == aiSymbol &&
                            board[row - 1][col - 1] == aiSymbol &&
                            board[row - 2][col - 2] == aiSymbol &&
                            board[row - 3][col - 3] == aiSymbol) {
                        hasWon = true;
                    }
            }
        }
        if (hasWon) {
            winner = Messages.PC;
            lastWinner = Messages.PC;
        }

    }
}

