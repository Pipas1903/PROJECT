package Leaderboard;

import Common.Messages;

import java.io.*;

import static Common.Utils.*;

public class LeaderboardManager {

    // PASTED FROM PLAYER -> NEEDS CHANGES
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
