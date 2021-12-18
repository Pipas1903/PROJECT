package com.arcade.leaderboard;

import com.arcade.common.Constants;
import com.arcade.common.Messages;
import com.arcade.common.GamesInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LeaderboardManager {

    private static final int limit = 5;

    private static String readFile(GamesInfo leaderboardFile) {

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


    private static void insertScore(String nickName, int score, GamesInfo game) {

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

    public static void manageScores(GamesInfo game, String nickname, int score) throws IOException {
        if (isLeaderboardFull(game)) {
            insertScore(nickname, score, game);
            writeOrdered(game, orderScores(game));
            writeOrdered(game, Files.lines(Path.of(game.path)).limit(5).collect(Collectors.joining("\n")));
            return;
        }
        insertScore(nickname, score, game);
        writeOrdered(game, orderScores(game));
    }

    public static boolean isLeaderboardFull(GamesInfo game) throws IOException {
        return Files.lines(Path.of(game.path)).count() >= limit;
    }

    private static String orderScores(GamesInfo game) {
        String[] current = readFile(game).split("\n");

        return Arrays.stream(current)
                .map(s -> {
                    String[] split = s.split(" ");
                    return new AbstractMap.SimpleEntry<>(Integer.valueOf(split[0]), split[1]);
                })
                .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
                .map(e -> e.getKey() + " " + e.getValue())
                .collect(Collectors.joining("\n"));

        /* FIRST IMPLEMENTATION
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

        return String.join("\n", scoreArray);*/
    }


    private static void writeOrdered(GamesInfo game, String orderedScores) {
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

    public static void printScores(GamesInfo game) {
        String[] leaderboard = readFile(game).split("\n");
        AtomicInteger counter = new AtomicInteger(1);
        String results = Arrays.stream(leaderboard)
                .map(s -> {
                    String[] split = s.split(" ");
                    return new AbstractMap.SimpleEntry<>(Integer.valueOf(split[0]), split[1]);
                })
                .map(e -> Constants.ANSI_YELLOW + "top " + counter.getAndIncrement() + ": " + Constants.ANSI_GREEN + e.getKey() + Constants.ANSI_RESET + " points -> " + Constants.ANSI_CYAN + e.getValue() + Constants.ANSI_RESET)
                .collect(Collectors.joining("\n"));
        System.out.println(results);
    }
}

