package com.arcade.menu;

import com.arcade.common.GamesInfo;
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
        while (true) {
            System.out.println(Messages.TO_DO);
            System.out.println(Messages.INSERT_NUMBER);
            System.out.println(Messages.OPTION_1_2MENU);
            System.out.println(Messages.OPTION_2_2MENU);
            System.out.println(Messages.EXIT_OPTION);
            String choice = getChoice();
            switch (choice) {
                case "1":
                    System.out.println(Messages.WELCOME_TO_PLAYER_CREATION);
                    this.playerOne = PlayerManager.playerCreation();
                    System.out.println(Messages.SUCCESS);
                    thirdMenu();
                    break;
                case "2":
                    System.out.println(Messages.WELCOME_TO_PLAYER_SELECTION);
                    this.playerOne = PlayerManager.playerSelection();
                    System.out.println(Messages.SUCCESS);
                    thirdMenu();
                    break;
                case "0":
                    System.out.println(Messages.BYE);
                    return;
                default:
                    System.out.println(Messages.INVALID_INPUT);
                    break;
            }
        }
    }

    public void thirdMenu() {

        System.out.println(Messages.TO_DO);
        System.out.println(Messages.OPTION_1_3MENU);
        System.out.println(Messages.OPTION_2_3MENU);
        System.out.println(Messages.OPTION_3_3MENU);

        String choice = getChoice();

        switch (choice) {
            case "1":
                fourthMenu(GamesInfo.TIC_TAC_TOE);
                break;
            case "2":
                System.out.println("empty case 2");
                break;
            default:
                System.out.println("empty case 3");
                break;
        }
        /*
        * choose game
        game rules and point attribution
        *play*
        */

        // CALL FOURTH MENU
    }

    public void fourthMenu(GamesInfo game) {
        System.out.println(Messages.TO_DO);

        String choice = getChoice();

        switch (choice) {
            case "1":
                System.out.println(Messages.RULES);
                System.out.println(game.rules);
                System.out.println(Messages.POINT_ATTRIBUTION);
                System.out.println(Messages.POINT_ATTRIBUTION_RULES);
                fourthMenu(game);
                break;
            case "2":
                System.out.println("empty case 2");
                break;
            case "3":
                System.out.println("empty case 3");
                break;
        }


        // tic tac toe
        // see game rules
        // single player or multiplayer
        // leaderboard

    }

    public void fifthMenu() {
        // tic tac toe
        // rock paper scissors
        // 4 in line

        // see game rules
        // single player or multiplayer
        // leaderboard

    }

    public void sixthMenu() {

    }

    public void seventhMenu() {

    }

    public void eighthMenu() {
        /*play again?
        quit -> back to 2nd menu
        */
    }

    public void ninthMenu() {

    }

    private String getChoice() {

        String choice = Utils.scanString.nextLine();

        while (isInputInvalid(choice)) {
            System.out.println(Messages.INVALID_INPUT);
            System.out.println(Messages.TRY_AGAIN);
            choice = Utils.scanString.nextLine();
        }
        return choice;
    }

    private boolean isInputInvalid(String choice) {
        return !(choice.matches("[0-9]"));
    }
}
