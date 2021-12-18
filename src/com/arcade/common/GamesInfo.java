package com.arcade.common;


public enum GamesInfo {

    TIC_TAC_TOE(Constants.PATH_TO_LEADERBOARD_FILES + "tictactoe.txt", Messages.TIC_TAC_TOE_RULES),
    ROCK_PAPER_SCISSORS(Constants.PATH_TO_LEADERBOARD_FILES + "rockpaperscissors.txt", Messages.ROCK_PAPER_SCISSORS_RULES),
    FOUR_IN_LINE(Constants.PATH_TO_LEADERBOARD_FILES + "fourinline.txt", Messages.FOUR_IN_LINE_RULES);

    public String path;
    public String rules;

    GamesInfo(String path, String rules) {
        this.path = path;
        this.rules = rules;
    }

}
