package com.arcade.games.rockPaperScissors;

import com.arcade.common.Constants;
import com.arcade.common.Messages;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.io.IOException;

public class RockPaperScissors {
    private Player playerOne;
    private Player playerTwo;

    public RockPaperScissors(Player player) {
        this.playerOne = player;
    }

    public RockPaperScissors(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void startGame1Players() {


    }

    public void startGame2Players() {

    }
}
