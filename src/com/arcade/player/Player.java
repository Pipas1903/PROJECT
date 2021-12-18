package com.arcade.player;

public class Player {

    private String nickname;
    private int score;
    private String symbol;
    private int consecutiveRoundsWon;

    public Player(String nickname) {
        this.nickname = nickname;
        this.score = 0;
    }

    public Player(){}

    public int getConsecutiveRoundsWon() {
        return consecutiveRoundsWon;
    }

    public void setConsecutiveRoundsWon(int consecutiveRoundsWon) {
        this.consecutiveRoundsWon = consecutiveRoundsWon;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
