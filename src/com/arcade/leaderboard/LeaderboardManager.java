package com.arcade.leaderboard;

import com.arcade.common.Messages;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LeaderboardManager {

    private static final int limit = 5;

    // PASTED FROM PLAYER -> NEEDS CHANGES
    private static String readFile(LeaderboardPaths leaderboardFile) {

        String line = "";
        String result = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(leaderboardFile.path));

            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
        return result;
    }


    public static void insertScore(String nickName, int score, LeaderboardPaths game) {

        String temp = readFile(game);
        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter(new FileWriter(game.path));
            writer.write(temp);
            writer.write(score + " " + nickName);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
    }

    public static void manageScores(LeaderboardPaths game, String nickname, int score) throws IOException {
        if (isLeaderboardFull(game)) {
            insertScore(nickname, score, game);
            writeOrdered(game, orderScores(game));
            writeOrdered(game, Files.lines(Path.of(game.path)).limit(5).collect(Collectors.joining("\n")));
            return;
        }
        insertScore(nickname, score, game);
        writeOrdered(game, orderScores(game));
    }

    public static boolean isLeaderboardFull(LeaderboardPaths game) throws IOException {
        return Files.lines(Path.of(game.path)).count() >= limit;
    }

    public static String orderScores(LeaderboardPaths game) {
        String[] current = readFile(game).split("\n");
        String[][] separated = new String[current.length][2];

        for (int i = 0; i < separated.length; i++) separated[i] = current[i].split(" ");

        boolean isUnfinished = true;
        while (isUnfinished) {
            isUnfinished = false;
            for (int i = 0; i < separated.length - 1; i++) {
                if (Integer.parseInt(separated[i][0]) < Integer.parseInt(separated[i + 1][0])) {
                    String temp = separated[i][0];
                    separated[i][0] = separated[i + 1][0];
                    separated[i + 1][0] = temp;
                    isUnfinished = true;
                }
            }
        }

        String[] scoreArray = new String[separated.length];
        for (int i = 0; i < separated.length; i++) scoreArray[i] = String.join(" ", separated[i]);

        return String.join("\n", scoreArray);
    }


    public static void writeOrdered(LeaderboardPaths game, String orderedScores) {
        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter(new FileWriter(game.path));
            writer.write(orderedScores);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println(Messages.UNEXPECTED_ERROR);
        }
    }
}

