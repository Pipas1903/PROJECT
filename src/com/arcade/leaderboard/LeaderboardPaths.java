package com.arcade.leaderboard;

public enum LeaderboardPaths {

    TIC_TAC_TOE ("src/com/arcade/leaderboard/tictactoe.txt"),
    ROCK_PAPER_SCISSORS("src/com/arcade/leaderboard/rockpaperscissors.txt"),
    FOUR_IN_LINE("src/com/arcade/leaderboard/fourinline.txt");

    String path;

    LeaderboardPaths(String path){
        this.path = path;
    }
}
