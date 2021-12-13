package Arcade;

import Common.Messages;
import Player.*;
import java.util.Scanner;

public class Arcade {

    private Player playerOne;
    private Player playerTwo;

    public void on() {
        System.out.println(Messages.WELCOME_TO_ARCADE + "\n" + Messages.PRESS_ENTER);
        /*“WELCOME TO THE ARCADE”
       “Press enter to start”
        */
        // CALL SECOND MENU
    }

    public void secondMenu() {
        /*
         *   existing player  -> u can see player score history
         *   create new player
         *   see high scores
         *   quit arcade
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
