package com.arcade.player;

import com.arcade.common.*;
import com.arcade.common.GamesInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.stream.Collectors;

import static com.arcade.common.Utils.*;

public class PlayerManager {

    public static Player playerCreation() {

        boolean invalidNickname;
        String nickName;

        do {
            invalidNickname = false;
            System.out.print(Messages.CHOOSE_NICKNAME);
            nickName = scanString.nextLine();

            if (checkIfPlayerProfileExists(convertToFileName(nickName))) {
                System.out.println(Messages.NICKNAME_ALREADY_EXISTS);
                invalidNickname = true;
            }

            if (nickName.length() != 3) {
                System.out.println(Messages.INVALID_INPUT + "\n" + Messages.TRY_AGAIN);
                invalidNickname = true;
            }

        } while (invalidNickname);

        createPlayerFile(nickName);
        return new Player(nickName);
    }


    public static Player playerSelection() {

        System.out.print(Messages.NICKNAME);
        String nickName = scanString.nextLine();

        while (!checkIfPlayerProfileExists(convertToFileName(nickName))) {
            System.out.println(Messages.NICKNAME_NOT_FOUND);
            System.out.print(Messages.NICKNAME);
            nickName = scanString.nextLine();
        }
        return new Player(nickName);
    }


    private static void createPlayerFile(String name) {

        new File(Constants.PATH_TO_PLAYER_FILES + convertToFileName(name));
        // rethink exceptions !!!!!!!
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.PATH_TO_PLAYER_FILES + convertToFileName(name)));
            writer.write(name);
            writer.newLine();
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
    }


    private static boolean checkIfPlayerProfileExists(String name) {
        return Files.exists(Path.of(Constants.PATH_TO_PLAYER_FILES + name));
    }


    private static String readFromPlayerFile(String nickName) {

        String line = "";
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.PATH_TO_PLAYER_FILES + convertToFileName(nickName)));

            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
        return result;
    }


    public static void addScoreToPlayerFile(String nickName, int score, GamesInfo game) {

        String temp = readFromPlayerFile(nickName);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.PATH_TO_PLAYER_FILES + convertToFileName(nickName)));
            writer.write(temp);
            switch (game) {
                case TIC_TAC_TOE:
                    writer.write(Constants.TIC_TAC_TOE + " " + score);
                    break;
                case ROCK_PAPER_SCISSORS:
                    writer.write(Constants.ROCK_PAPER_SCISSORS + " " + score);
                    break;
                case FOUR_IN_LINE:
                    writer.write(Constants.FOUR_IN_LINE + " " + score);
                    break;
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printPlayerScoreHistory(String nickName) {
        String temp = readFromPlayerFile(nickName);

        String results = temp.lines().filter(s -> !s.equals(nickName))
                .map(s -> {
                    String[] split = s.split(" ");
                    return new AbstractMap.SimpleEntry<>(String.join(" ",split[0],split[1],split[2]), split[3]);
                })
                .map(e -> Constants.ANSI_YELLOW + "game: " + Constants.ANSI_GREEN + e.getKey() + Constants.ANSI_RESET + " points -> " + Constants.ANSI_CYAN + e.getValue() + Constants.ANSI_RESET)
                .collect(Collectors.joining("\n"));

        System.out.println(results);
    }
}