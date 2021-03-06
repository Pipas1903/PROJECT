package com.arcade.common;

public class Messages {

    public static final String WELCOME_TO_PLAYER_CREATION = "Welcome to player creation!";
    public static final String CHOOSE_NICKNAME = "Insert a 3 character nickname: ";
    public static final String WELCOME_TO_PLAYER_SELECTION = "Welcome to player selection!";
    public static final String WELCOME_TO_GAME_SELECTION = Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "Which game do you wish to play?" + Constants.ANSI_RESET;
    public static final String BYE = Constants.ANSI_GREEN_BACKGROUND + Constants.ANSI_BLACK + "Bye! Until next time!" + Constants.ANSI_RESET;

    // INVALID INPUT MESSAGES
    public static final String INVALID_INPUT = Constants.ANSI_RED + "INVALID INPUT " + Constants.ANSI_RESET;
    public static final String TRY_AGAIN = Constants.ANSI_RED + "please try again" + Constants.ANSI_RESET;
    public static final String NICKNAME_ALREADY_EXISTS = Constants.ANSI_RED + "NICKNAME ALREADY EXISTS" + Constants.ANSI_RESET;
    public static final String UNEXPECTED_ERROR = Constants.ANSI_RED + "UNEXPECTED ERROR. RESTART THE GAME." + Constants.ANSI_RESET;
    public static final String NICKNAME_NOT_FOUND = Constants.ANSI_RED + "NICKNAME NOT FOUND" + Constants.ANSI_RESET;
    public static final String CANT_CHOOSE_SAME_PLAYER = Constants.ANSI_RED + "YOU CAN'T SELECT THE SAME PLAYER" + Constants.ANSI_RESET;

    public static final String WARNING = Constants.ANSI_RED + "Warning! To play rock paper scissors with 2 players, you need another machine!" + Constants.ANSI_RESET + "\nTo connect enter 'nc 127.0.0.1 92' on the other machine terminal.";

    public static final String SUCCESS = Constants.ANSI_GREEN + "OPERATION SUCCESSFUL" + Constants.ANSI_RESET;


    public static final String ARCADE = "\n" + Constants.ANSI_YELLOW +
            " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.\n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |      __      | || |  _______     | || |     ______   | || |      __      | || |  ________    | || |  _________   | |\n" +
            "| |     /  \\     | || | |_   __ \\    | || |   .' ___  |  | || |     /  \\     | || | |_   ___ `.  | || | |_   ___  |  | |\n" +
            "| |    / /\\ \\    | || |   | |__) |   | || |  / .'   \\_|  | || |    / /\\ \\    | || |   | |   `. \\ | || |   | |_  \\_|  | |\n" +
            "| |   / ____ \\   | || |   |  __ /    | || |  | |         | || |   / ____ \\   | || |   | |    | | | || |   |  _|  _   | |\n" +
            "| | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |  \\ `.___.'\\  | || | _/ /    \\ \\_ | || |  _| |___.' / | || |  _| |___/ |  | |\n" +
            "| ||____|  |____|| || | |____| |___| | || |   `._____.'  | || ||____|  |____|| || | |________.'  | || | |_________|  | |\n" +
            "| |              | || |              | || |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'" + Constants.ANSI_RESET;

    public static final String PRESS_ENTER = " *press enter to continue*  ";

    public static final String NICKNAME = "Nickname: ";

    // ALL GAMES
    public static final String ARE_YOU_READY = Constants.ANSI_BLUE_BACKGROUND + Constants.ANSI_BLACK + "Are you ready?" + Constants.ANSI_RESET + "\n" + Constants.ANSI_BLUE_BACKGROUND + Constants.ANSI_BLACK + "Let's play: " + Constants.ANSI_RESET;
    public static final String GREAT_GAME = Constants.ANSI_GREEN_BACKGROUND + Constants.ANSI_BLACK + "Great game! Until next time!" + Constants.ANSI_RESET;
    public static final String ROUND = "ROUND ";

    // TIC TAC TOE MESSAGES
    public static final String CHOOSE_SYMBOL = "which symbol would you like to use? ";
    public static final String CHOOSE_COORDINATES = Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "please enter the coordinates where you want to place your piece: " + Constants.ANSI_RESET;
    public static final String OCCUPIED_TILE = Constants.ANSI_RED + "That tile is already occupied! Try another one!" + Constants.ANSI_RESET;
    public static final String ILLEGAL_MOVE = Constants.ANSI_RED + "That move is ilegal!" + Constants.ANSI_RESET;
    public static final String TIE = "tie";
    public static final String TIE_ANNOUNCE = "It's a tie!";
    public static final String ANNOUNCE_WINNER = "The winner is: ";
    public static final String PC = "PC";

    // ROCK PAPER SCISSORS MESSAGES
    public static final String WELCOME_TO_ROCK_PAPER_SCISSORS = "Welcome to Rock, Paper, Scissors!";
    public static final String REMEMBER = "Remember, to play with 2 players, you need two computers, so make sure the second player is connected to start playing!";
    public static final String CONNECTED = Constants.ANSI_GREEN + "PLAYER TWO CONNECTED" + Constants.ANSI_RESET;
    public static final String WAITING = "...waiting for someone to connect...";
    public static final String ENTER_YOUR_MOVE = " enter your move";
    public static final String WAITING_FOR_PLAYER_1= "...waiting for player one...";
    public static final String WAITING_FOR_PLAYER_2= "...waiting for player two...";
    public static final String ROCK_PAPER_SCISSORS_WARNING_NUMBER_OF_PLAYERS = "You'll still have to create or select a player profile for player 2, so that their history of scores can be updated\nand they can participate on the leaderboard.";


    // GAME RULES
    public static final String RULES = "RULES: ";
    public static final String POINT_ATTRIBUTION = "POINT ATTRIBUTION: ";
    public static final String TIC_TAC_TOE_RULES = "Each player takes a turn to put his mark. The first one to make a row, wins.";
    public static final String ROCK_PAPER_SCISSORS_RULES = "There's 3 possible moves: Rock, Paper or Scissors.\nEach player chooses their move and plays at the same time.\nRock wins Scissors, Scissors wins Paper, Paper wins Rocks.\nIf players choose the same move, it's a tie.";
    public static final String FOUR_IN_LINE_RULES = "Each player takes a turn to put his mark. The first one to make a row of four (either horizontally, vertically or diagonally) wins.\nHowever, moves start from bottom to top making pieces either stacked on each other, or on the base of the board.)";
    public static final String POINT_ATTRIBUTION_RULES = "Each round won counts as 21 points. If you win several rounds in a row, your points will scale exponentially.";

    // MENUS
    public static final String TO_DO = Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "What do you wish to do?" + Constants.ANSI_RESET;
    public static final String INSERT_NUMBER = "---insert number---";
    public static final String EXIT_OPTION = "0. quit";
    public static final String BACK_OPTION = "Back";
    public static final String SEE_PLAYER_LEADERBOARD = "See player score history";

    public static final String HOW_MANY_PLAYERS = Constants.ANSI_WHITE_BACKGROUND + Constants.ANSI_BLACK + "How many players will there be?" + Constants.ANSI_RESET;
    public static final String ONE_PLAYER = "1. one player";
    public static final String TWO_PLAYERS = "2. two players";

    public static final String CREATE_PLAYER = "1. create a player";
    public static final String SELECT_PLAYER = "2. select a player";

    public static final String TOP_SCORES = "      TOP SCORES";

    public static final String PLAY_TIC_TAC_TOE = "1. Play " + Constants.TIC_TAC_TOE;
    public static final String PLAY_ROCK_PAPER_SCISSORS = "2. Play " + Constants.ROCK_PAPER_SCISSORS;
    public static final String PLAY_FOUR_IN_LINE = "3. Play " + Constants.FOUR_IN_LINE;

    public static final String SEE_GAME_RULES = "1. See game rules.";
    public static final String SEE_POINT_RULES = "4. See point attribution system.";
    public static final String PLAY = "2. Play";
    public static final String SEE_GAME_LEADERBOARD = "See game leaderboard.";


}
