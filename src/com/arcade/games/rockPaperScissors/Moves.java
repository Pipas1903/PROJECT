package com.arcade.games.rockPaperScissors;

import com.arcade.common.Messages;

public enum Moves {
    ROCK("SCISSORS"),
    PAPER("ROCK"),
    SCISSORS("PAPER");

    String wins;

    Moves(String wins) {
        this.wins = wins;
    }

    public String play(Moves PCMove, String playernickname) {
        if (this.wins.equals(PCMove.toString())) {
            return playernickname;
        }
        return (this.equals(PCMove) ? Messages.TIE : Messages.PC);
    }
    public static Moves getRandomMove() {
        return Moves.values()[(int) ((Math.floor(Math.random() * (2 - 0 + 1) + 0)))];
    }
}
