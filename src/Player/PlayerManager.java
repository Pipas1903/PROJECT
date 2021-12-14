package Player;

import Common.Messages;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static Common.Utils.*;

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

        new File(Messages.PATH + convertToFileName(name));

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(Messages.PATH + convertToFileName(name)));
            writer.write( name);
            writer.newLine();
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
    }


    private static boolean checkIfPlayerProfileExists(String name) {
        return Files.exists(Path.of(Messages.PATH + name));
    }


    public static String readFromPlayerFile(String nickName) {

        String line = "";
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Messages.PATH + convertToFileName(nickName)));

            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
        return result;
    }


    public static void addScoreToPlayerFile(String nickName, int score, String gameName) {
        String temp = readFromPlayerFile(nickName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Messages.PATH + convertToFileName(nickName)));
            writer.write(temp);
            if (gameName.equals(Messages.TIC_TAC_TOE))
                writer.write( Messages.TIC_TAC_TOE + " " + score);
            else writer.write( Messages.ROCK_PAPER_SCISSORS + " " + score);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
