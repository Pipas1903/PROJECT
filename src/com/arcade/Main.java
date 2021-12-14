package com.arcade;

import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.leaderboard.LeaderboardPaths;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //new Menu().secondMenu();
        //PlayerManager.addScoreToPlayerFile("pip", 98765, Constants.TIC_TAC_TOE);
        //PlayerManager.addScoreToPlayerFile("pip", 9876, Constants.TIC_TAC_TOE);
        //PlayerManager.addScoreToPlayerFile("pip", 5, Constants.ROCK_PAPER_SCISSORS);
        //LeaderboardManager.insertScore("pip", 8765, LeaderboardPaths.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("toc", 9876, LeaderboardPaths.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("pip", 123, LeaderboardPaths.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("sop", 10, LeaderboardPaths.TIC_TAC_TOE);
        //LeaderboardManager.insertScore("sop", 1, LeaderboardPaths.TIC_TAC_TOE);
        LeaderboardManager.manageScores(LeaderboardPaths.TIC_TAC_TOE, "cop", 12098375);
        // System.out.println(PlayerManager.readFromPlayerFile("pip"));
    }
}
