package com.arcade.leaderboard;

import com.arcade.common.Constants;
import com.arcade.common.Messages;

import java.io.*;

import static com.arcade.common.Utils.*;

public class LeaderboardManager {


    public LeaderboardManager() {

    }


    // PASTED FROM PLAYER -> NEEDS CHANGES
    public static String readFile(String nickName) {

        String line = "";
        String result = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.PATH_TO_LEADERBOARD_FILES + convertToFileName(nickName)));

            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
        return result;
    }


    public static void insertScore(String nickName, int score, String gameName) {

        String temp;
        BufferedWriter writer = null;

       /* try {

            writer = new BufferedWriter(new FileWriter(Messages.PATH + ticTacToeLeaderboard));
            temp = readFile();
            writer.write(temp);
            writer.write(score);

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }*/
    }

    public static void verifyScores() {

    }

    public static void orderScores() {

    }
}
