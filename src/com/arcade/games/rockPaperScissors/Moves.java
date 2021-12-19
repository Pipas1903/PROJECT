package com.arcade.games.rockPaperScissors;

public enum Moves {
    ROCK("SCISSORS"),
    PAPER("ROCK"),
    SCISSORS("PAPER");

    String wins;

    Moves(String wins) {
        this.wins = wins;
    }
}
