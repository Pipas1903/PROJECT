package com.arcade;

import com.arcade.common.Constants;
import com.arcade.common.Utils;
import com.arcade.games.ticTacToe.TicTacToe;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.common.GamesInfo;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.io.IOException;
import java.util.LinkedList;

public class Main {
    static String[][] board = new String[3][3];
    static private String aiSymbol = "O";
    static int y;
    static int x;


    private static boolean isMoveIlegal(String coordinates) {
        return !(coordinates.matches("[1-9]"));
    }

    private static String blockPlayer() {
        LinkedList<String> freeTiles = new LinkedList<>();
        if (board[0][0].equals(board[x][y])) {
            if (!isMoveIlegal(board[0][1])) freeTiles.add(board[0][1]);
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[1][0])) freeTiles.add(board[1][0]);

            return (pcChooseTile(freeTiles));
        }
        if (board[0][1].equals(board[x][y])) {
            if (!isMoveIlegal(board[0][0])) freeTiles.add(board[0][0]);
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[0][2])) freeTiles.add(board[0][2]);
            return (pcChooseTile(freeTiles));
        }
        if (board[0][2].equals(board[x][y])) {
            if (!isMoveIlegal(board[0][1])) freeTiles.add(board[0][1]);
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[1][2])) freeTiles.add(board[1][2]);
            return (pcChooseTile(freeTiles));
        }

        if (board[1][0].equals(board[x][y])) {
            if (!isMoveIlegal(board[0][0])) freeTiles.add(board[0][0]);
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[2][1])) freeTiles.add(board[2][1]);
            return (pcChooseTile(freeTiles));
        }
        if (board[1][1].equals(board[x][y])) {
            if (!isMoveIlegal(board[0][1])) freeTiles.add(board[0][1]);
            if (!isMoveIlegal(board[1][0])) freeTiles.add(board[1][0]);
            if (!isMoveIlegal(board[1][2])) freeTiles.add(board[1][2]);
            if (!isMoveIlegal(board[2][1])) freeTiles.add(board[2][1]);
            return (pcChooseTile(freeTiles));
        }
        if (board[1][2].equals(board[x][y])) {
            if (!isMoveIlegal(board[0][2])) freeTiles.add(board[0][2]);
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[2][2])) freeTiles.add(board[2][2]);

            return (pcChooseTile(freeTiles));
        }

        if (board[2][0].equals(board[x][y])) {
            if (!isMoveIlegal(board[1][0])) freeTiles.add(board[1][0]);
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[2][1])) freeTiles.add(board[2][1]);
            return (pcChooseTile(freeTiles));
        }
        if (board[2][1].equals(board[x][y])) {
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[2][0])) freeTiles.add(board[2][0]);
            if (!isMoveIlegal(board[2][2])) freeTiles.add(board[2][2]);
            return (pcChooseTile(freeTiles));
        }
        if (board[2][2].equals(board[x][y])) {
            if (!isMoveIlegal(board[1][1])) freeTiles.add(board[1][1]);
            if (!isMoveIlegal(board[1][2])) freeTiles.add(board[1][2]);
            if (!isMoveIlegal(board[2][1])) freeTiles.add(board[2][1]);
            return (pcChooseTile(freeTiles));
        }

        return pcChooseTile(aiFindPossibleMoves());
    }

    // new
    private static LinkedList<String> aiFindPossibleMoves() {
        LinkedList<String> freeTiles = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!isMoveIlegal(board[i][j])) freeTiles.add(board[i][j]);
            }
        }
        return freeTiles;
    }

    // new
    private static String pcChooseTile(LinkedList<String> allFreeTiles) {
        int randomNumber = (int) Math.floor(Math.random() * (allFreeTiles.size()));
        return allFreeTiles.get(randomNumber);
    }

    //new
    private static void pcPlacePieceInBoard(String coordinates) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                if (board[i][j].equals(coordinates)) {
                    board[i][j] = aiSymbol;
                    return;
                }
        }
    }

    private static void draw() {

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
            System.out.println("--+---+--");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void startRound1Player() {

        boolean play = true;
        do {
            clearScreen();
            draw();
            y = Utils.scanInt.nextInt();
            x = Utils.scanInt.nextInt();
            board[y][x] = "X";
            String coordinates = blockPlayer();
            pcPlacePieceInBoard(coordinates);
        } while (play);
    }

    public static void main(String[] args) throws IOException {
        // TicTacToe game = new TicTacToe(PlayerManager.playerSelection(), PlayerManager.playerSelection());
        // game.startGame2Players();
        TicTacToe game = new TicTacToe(PlayerManager.playerSelection());
        game.startGame1Players();

    }
    //new Menu().secondMenu();
    //PlayerManager.addScoreToPlayerFile("pip", 98765, Constants.TIC_TAC_TOE);
    //PlayerManager.addScoreToPlayerFile("pip", 9876, Constants.TIC_TAC_TOE);
    //PlayerManager.addScoreToPlayerFile("pip", 5, Constants.ROCK_PAPER_SCISSORS);
    // LeaderboardManager.insertScore("pip", 8765, GamesInfo.TIC_TAC_TOE);
    // LeaderboardManager.insertScore("toc", 9876, GamesInfo.TIC_TAC_TOE);
    // LeaderboardManager.insertScore("pip", 123, GamesInfo.TIC_TAC_TOE);
    // LeaderboardManager.insertScore("sop", 10, GamesInfo.TIC_TAC_TOE);
    // LeaderboardManager.insertScore("sop", 1, GamesInfo.TIC_TAC_TOE);
    //LeaderboardManager.insertScore("sop", 102987, GamesInfo.TIC_TAC_TOE);
    //LeaderboardManager.manageScores(GamesInfo.TIC_TAC_TOE, "cop", 12098375);
    // System.out.println(PlayerManager.readFromPlayerFile("pip"));
    //TicTacToe game = new TicTacToe(PlayerManager.playerCreation(), PlayerManager.playerCreation());
    //game.chooseSymbol();
    // this method draws the board
    // TicTacToe game = new TicTacToe(PlayerManager.playerCreation(), PlayerManager.playerSelection());
    // game.startGame();
}


