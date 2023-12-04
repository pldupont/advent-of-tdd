package com.pld.adventoftdd.day2;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private final int id;
    private final Map<String, Integer> maxes = new HashMap<>();

    public Game(int id, String rounds) {
        this.id = id;
        if (StringUtils.isNotEmpty(rounds)) {
            Arrays.stream(rounds.split(";")).forEach(this::processRound);
        }
    }

    public static Game readGameLine(String gameLine) {
        if (StringUtils.isNotBlank(gameLine)) {
            Pattern gamePattern = Pattern.compile("Game (\\d+):(.*)");
            Matcher gameMatcher = gamePattern.matcher(gameLine);
            if (gameMatcher.matches()) {
                return new Game(NumberUtils.toInt(gameMatcher.group(1)), gameMatcher.group(2));
            }
        }
        return null;
    }

    private void processRound(String rounds) {
        Arrays.stream(rounds.split(","))
                .map(String::trim)
                .map(r -> r.split(" "))
                .forEach(r -> this.updateMax(r[1], NumberUtils.toInt(r[0])));
    }

    private void updateMax(String color, int count) {
        maxes.put(color, Math.max(count, getMax(color)));
    }

    public int getId() {
        return id;
    }

    public int getMax(String color) {
        return maxes.getOrDefault(color, 0);
    }

    public int getMinimalPower() {
        return getMax("red") * getMax("green") * getMax("blue");
    }
}
