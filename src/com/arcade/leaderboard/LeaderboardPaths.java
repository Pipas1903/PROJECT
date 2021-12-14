package com.arcade.leaderboard;


import com.arcade.common.Constants;

public enum LeaderboardPaths {

    TIC_TAC_TOE(Constants.PATH_TO_LEADERBOARD_FILES + "tictactoe.txt"),
    ROCK_PAPER_SCISSORS(Constants.PATH_TO_LEADERBOARD_FILES + "rockpaperscissors.txt"),
    FOUR_IN_LINE(Constants.PATH_TO_LEADERBOARD_FILES + "fourinline.txt");

    String path;

    LeaderboardPaths(String path) {
        this.path = path;
    }
}
