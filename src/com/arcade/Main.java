package com.arcade;
import com.arcade.common.Constants;
import com.arcade.menu.Menu;
import com.arcade.player.PlayerManager;

public class Main {
    public static void main(String[] args) {

        new Menu().secondMenu();
        PlayerManager.addScoreToPlayerFile("pip", 98765, Constants.TIC_TAC_TOE);
        PlayerManager.addScoreToPlayerFile("pip", 9876, Constants.TIC_TAC_TOE);
        PlayerManager.addScoreToPlayerFile("pip", 5, Constants.ROCK_PAPER_SCISSORS);

        // System.out.println(PlayerManager.readFromPlayerFile("pip"));
    }
}
