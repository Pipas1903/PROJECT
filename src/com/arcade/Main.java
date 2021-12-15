package com.arcade;

import com.arcade.games.ticTacToe.TicTacToe;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.common.GamesInfo;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //new Menu().secondMenu();
        //PlayerManager.addScoreToPlayerFile("pip", 98765, Constants.TIC_TAC_TOE);
        //PlayerManager.addScoreToPlayerFile("pip", 9876, Constants.TIC_TAC_TOE);
        //PlayerManager.addScoreToPlayerFile("pip", 5, Constants.ROCK_PAPER_SCISSORS);
        //LeaderboardManager.insertScore("pip", 8765, GamesInfo.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("toc", 9876, GamesInfo.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("pip", 123, GamesInfo.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("sop", 10, GamesInfo.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("sop", 1, GamesInfo.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("sop", 102987, GamesInfo.TIC_TAC_TOE);
        //LeaderboardManager.manageScores(GamesInfo.TIC_TAC_TOE, "cop", 12098375);
        // System.out.println(PlayerManager.readFromPlayerFile("pip"));
        //TicTacToe game = new TicTacToe(PlayerManager.playerCreation(), PlayerManager.playerCreation());
        //game.chooseSymbol();

        // this method draws the board
        TicTacToe game = new TicTacToe(PlayerManager.playerSelection(), PlayerManager.playerSelection());
        game.startGame();
    }
}

