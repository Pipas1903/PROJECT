package com.arcade.player;

import java.io.File;
import java.nio.file.Files;

public class Player {

    private String nickname;
    private int score;
    private char symbol;
    private int consecutiveRoundsWon;

    public int getConsecutiveRoundsWon() {
        return consecutiveRoundsWon;
    }

    public void setConsecutiveRoundsWon(int consecutiveRoundsWon) {
        this.consecutiveRoundsWon = consecutiveRoundsWon;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Player(String nickname) {
        this.nickname = nickname;
        this.score = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public int getCurrentScore() {
        return score;
    }

    public void setCurrentScore(int currentScore) {
        this.score = currentScore;
    }
}
