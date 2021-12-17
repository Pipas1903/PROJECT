package com.arcade;

import com.arcade.common.GamesInfo;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.menu.Menu;
import com.arcade.player.PlayerManager;

import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException {
        //TicTacToe game = new TicTacToe(PlayerManager.playerSelection(), PlayerManager.playerSelection());
        //game.startGame2Players();
        //Scanner scan = new Scanner(file);

        new Menu().on();
        //PlayerManager.printPlayerScoreHistory("pip");
        //LeaderboardManager.printScores(GamesInfo.TIC_TAC_TOE);
    }

}


