package com.arcade.menu;

import com.arcade.common.Messages;
import com.arcade.common.Utils;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

public class Menu {

    private Player playerOne;
    private Player playerTwo;

    public void on() {
        System.out.println(Messages.WELCOME_TO_ARCADE + "\n" + Messages.PRESS_ENTER);
        Utils.scanString.nextLine();
        secondMenu();
    }

    public void secondMenu() {

        /*
         *   existing com.arcade.arcade.player  -> u can see com.arcade.arcade.player score history
         *   create new com.arcade.arcade.player
         *   see high scores
         *   quit com.arcade.arcade
         */
        // CALL THIRD MENU



        // PLAYER CREATION
        System.out.println(Messages.WELCOME_TO_PLAYER_CREATION);
        this.playerOne = PlayerManager.playerCreation();
        System.out.println(Messages.SUCCESS);

        // PLAYER SELECTION
        //  System.out.println(Messages.WELCOME_TO_PLAYER_SELECTION);
        // this.playerOne = PlayerManager.playerSelection();
    }

    public void thirdMenu() {
        /*
        * choose game
        game rules and point attribution
        *play*
        */

        // CALL FOURTH MENU
    }

    public void fourthMenu() {
        /*play again?
        quit -> back to 2nd menu
        */
    }
}
