package com.arcade.menu;

import com.arcade.common.Constants;
import com.arcade.common.GamesInfo;
import com.arcade.common.Messages;
import com.arcade.common.Utils;
import com.arcade.games.fourInLine.FourInLine;
import com.arcade.games.rockPaperScissors.RockPaperScissors;
import com.arcade.games.ticTacToe.TicTacToe;
import com.arcade.leaderboard.LeaderboardManager;
import com.arcade.player.Player;
import com.arcade.player.PlayerManager;

import java.io.IOException;

public class Menu {

    private Player playerOne;
    private Player playerTwo;
    private int numberOfPlayers;


    public void on() throws IOException {
        System.out.println(Messages.ARCADE);
        System.out.println(Messages.PRESS_ENTER);
        Utils.scanString.nextLine();
        chooseNumberOfPlayers();
    }

    public void chooseNumberOfPlayers() throws IOException {

        while (true) {

            System.out.println(Messages.WARNING);
            System.out.println(Messages.ROCK_PAPER_SCISSORS_WARNING_NUMBER_OF_PLAYERS);
            System.out.println();
            System.out.println(Messages.HOW_MANY_PLAYERS);
            System.out.println(Messages.ONE_PLAYER);
            System.out.println(Messages.TWO_PLAYERS);
            System.out.println(Constants.ANSI_RED + Messages.EXIT_OPTION + Constants.ANSI_RESET);
            System.out.println();
            System.out.println(Messages.INSERT_NUMBER);
            String choice = getChoice();

            switch (choice) {

                case "1":
                    numberOfPlayers = 1;
                    System.out.println();
                    System.out.println(Constants.ANSI_PURPLE + "Player one " + Constants.ANSI_RESET);
                    this.playerOne = createSelectPlayer();
                    System.out.println();
                    if (playerOne == null) {
                        return;
                    }
                    chooseGame();
                    break;

                case "2":
                    System.out.println();
                    numberOfPlayers = 2;
                    System.out.println(Constants.ANSI_PURPLE + "Player one " + Constants.ANSI_RESET);
                    this.playerOne = createSelectPlayer();
                    System.out.println();
                    if (playerOne == null) {
                        return;
                    }
                    System.out.println(Constants.ANSI_BLUE + "Player two " + Constants.ANSI_RESET);
                    this.playerTwo = createSelectPlayer();
                    System.out.println();
                    if (playerTwo == null) {
                        return;
                    }
                    chooseGame();
                    break;

                case "0":
                    System.out.println();
                    System.out.println(Messages.BYE);
                    System.exit(1);

                default:
                    System.out.println();
                    System.out.println(Messages.INVALID_INPUT);
                    System.out.println();
                    break;
            }
        }
    }

    public Player createSelectPlayer() throws IOException {

        while (true) {

            System.out.println(Messages.TO_DO);
            System.out.println(Messages.CREATE_PLAYER);
            System.out.println(Messages.SELECT_PLAYER);
            System.out.println("3. " + Messages.BACK_OPTION);
            System.out.println(Constants.ANSI_RED + Messages.EXIT_OPTION + Constants.ANSI_RESET);
            System.out.println();
            System.out.println(Messages.INSERT_NUMBER);
            String choice = getChoice();

            switch (choice) {

                case "1":
                    System.out.println();
                    System.out.println(Messages.WELCOME_TO_PLAYER_CREATION);
                    Player playerOpt1 = PlayerManager.playerCreation();
                    System.out.println(Messages.SUCCESS);
                    return playerOpt1;

                case "2":
                    System.out.println();
                    System.out.println(Messages.WELCOME_TO_PLAYER_SELECTION);
                    Player playerOpt2 = PlayerManager.playerSelection();

                    if (numberOfPlayers == 2 && playerOne != null) {
                        while (playerOpt2.getNickname().equals(playerOne.getNickname())) {
                            System.out.println(Messages.CANT_CHOOSE_SAME_PLAYER);
                            playerOpt2 = PlayerManager.playerSelection();
                        }
                    }

                    System.out.println(Messages.SUCCESS);
                    return playerOpt2;

                case "3":
                    System.out.println();
                    chooseNumberOfPlayers();
                    break;

                case "0":
                    System.out.println();
                    System.out.println(Messages.BYE);
                    System.exit(1);

                default:
                    System.out.println();
                    System.out.println(Messages.INVALID_INPUT);
                    System.out.println();
                    break;
            }
        }
    }

    public void chooseGame() throws IOException {

        while (true) {

            System.out.println(Messages.WELCOME_TO_GAME_SELECTION);
            System.out.println(Messages.PLAY_TIC_TAC_TOE);
            System.out.println(Messages.PLAY_ROCK_PAPER_SCISSORS);
            System.out.println(Messages.PLAY_FOUR_IN_LINE);
            System.out.println(Messages.SEE_POINT_RULES);
            System.out.println("5. " + Messages.BACK_OPTION);
            System.out.println(Constants.ANSI_RED + Messages.EXIT_OPTION + Constants.ANSI_RESET);
            System.out.println();
            System.out.println(Messages.INSERT_NUMBER);

            String choice = getChoice();

            switch (choice) {

                case "1":
                    System.out.println();
                    gameOptions(GamesInfo.TIC_TAC_TOE);
                    break;

                case "2":
                    System.out.println();
                    if (numberOfPlayers == 2) {
                        System.out.println(Messages.WARNING);
                        Utils.scanString.nextLine();
                    }
                    gameOptions(GamesInfo.ROCK_PAPER_SCISSORS);
                    break;

                case "3":
                    System.out.println();
                    gameOptions(GamesInfo.FOUR_IN_LINE);
                    break;

                case "4":
                    System.out.println();
                    System.out.println(Messages.POINT_ATTRIBUTION);
                    System.out.println(Messages.POINT_ATTRIBUTION_RULES);
                    Utils.scanString.nextLine();
                    chooseGame();
                    break;
                case "5":
                    System.out.println();
                    this.playerOne = new Player();
                    this.playerTwo = new Player();
                    chooseNumberOfPlayers();
                    break;

                case "0":
                    System.out.println();
                    System.out.println(Messages.BYE);
                    System.exit(1);

                default:
                    System.out.println();
                    System.out.println(Messages.INVALID_INPUT);
                    System.out.println(Messages.TRY_AGAIN);
                    System.out.println();
                    break;
            }
        }
    }

    public void gameOptions(GamesInfo game) throws IOException {

        while (true) {
            System.out.println(Messages.TO_DO);
            System.out.println(Messages.SEE_GAME_RULES);
            System.out.println(Messages.PLAY);
            System.out.println("3. " + Messages.SEE_PLAYER_LEADERBOARD);
            System.out.println("4. " + Messages.SEE_GAME_LEADERBOARD);
            System.out.println("5. " + Messages.BACK_OPTION);
            System.out.println(Constants.ANSI_RED + Messages.EXIT_OPTION + Constants.ANSI_RESET);
            System.out.println();
            System.out.println(Messages.INSERT_NUMBER);

            String choice = getChoice();

            switch (choice) {
                case "1":
                    System.out.println();
                    System.out.println(Messages.RULES);
                    gameRules(game);
                    Utils.scanString.nextLine();
                    gameOptions(game);
                    break;

                case "2":
                    System.out.println();
                    play(game);
                    break;

                case "3":
                    System.out.println();
                    if (playerTwo != null) {
                        System.out.println(playerOne.getNickname());
                        seePlayerScoreHistory(playerOne);
                        System.out.println();
                        System.out.println(playerTwo.getNickname());
                        seePlayerScoreHistory(playerTwo);
                        Utils.scanString.nextLine();
                        break;
                    }
                    System.out.println(playerOne.getNickname());
                    seePlayerScoreHistory(playerOne);
                    Utils.scanString.nextLine();
                    break;

                case "4":
                    System.out.println();
                    System.out.println(Constants.ANSI_PURPLE + Messages.TOP_SCORES + Constants.ANSI_RESET);
                    LeaderboardManager.printScores(game);
                    Utils.scanString.nextLine();
                    break;

                case "5":
                    System.out.println();
                    chooseGame();
                    break;

                case "0":
                    System.out.println();
                    System.out.println(Messages.BYE);
                    System.exit(1);
                    return;

                default:
                    System.out.println();
                    System.out.println(Messages.INVALID_INPUT);
                    System.out.println(Messages.TRY_AGAIN);
                    break;
            }
        }

    }

    public void play(GamesInfo game) throws IOException {
        if (numberOfPlayers == 1) {
            switch (game) {
                case TIC_TAC_TOE:
                    System.out.println();
                    new TicTacToe(playerOne).startGame1Players();
                    break;
                case ROCK_PAPER_SCISSORS:
                    System.out.println();
                    new RockPaperScissors(playerOne).startGame1Players();
                    break;
                case FOUR_IN_LINE:
                    System.out.println();
                    new FourInLine(playerOne).startGame1Players();
                    break;
            }
            return;
        }

        switch (game) {
            case TIC_TAC_TOE:
                System.out.println();
                new TicTacToe(playerOne, playerTwo).startGame2Players();
                break;
            case ROCK_PAPER_SCISSORS:
                System.out.println();
                new RockPaperScissors(playerOne, playerTwo).startGame2Players();
                break;
            case FOUR_IN_LINE:
                System.out.println();
                new FourInLine(playerOne, playerTwo).startGame2Players();
                break;
        }
    }

    public void gameRules(GamesInfo game) {
        switch (game) {
            case TIC_TAC_TOE:
                System.out.println(Messages.TIC_TAC_TOE_RULES);
                break;
            case ROCK_PAPER_SCISSORS:
                System.out.println(Messages.ROCK_PAPER_SCISSORS_RULES);
                break;
            case FOUR_IN_LINE:
                System.out.println(Messages.FOUR_IN_LINE_RULES);
                break;
        }
    }

    public void seePlayerScoreHistory(Player player) {
        PlayerManager.printPlayerScoreHistory(player.getNickname());
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
