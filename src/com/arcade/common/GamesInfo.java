package com.arcade.common;


public enum GamesInfo {

    TIC_TAC_TOE(Constants.PATH_TO_LEADERBOARD_FILES + "tictactoe.txt"),
    ROCK_PAPER_SCISSORS(Constants.PATH_TO_LEADERBOARD_FILES + "rockpaperscissors.txt"),
    FOUR_IN_LINE(Constants.PATH_TO_LEADERBOARD_FILES + "fourinline.txt");

    public String path;

    GamesInfo(String path) {
        this.path = path;
    }
}
