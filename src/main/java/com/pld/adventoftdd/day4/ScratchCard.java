package com.pld.adventoftdd.day4;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScratchCard {
    private int id;
    private List<Integer> winningNumbers;
    private List<Integer> cardNumbers;
    private int nbCopies = 1;

    protected ScratchCard() {
    } // For unit test

    public ScratchCard(String aScratchCardString) {
        if (StringUtils.isBlank(aScratchCardString)) {
            throw new RuntimeException("Invalid card");
        }
        Pattern p = Pattern.compile("Card +(\\d+): ([\\d ]+) \\| ([\\d ]+)");
        Matcher m = p.matcher(aScratchCardString);
        if (!m.matches()) {
            throw new RuntimeException("Invalid card : " + aScratchCardString);
        }
        this.id = Integer.parseInt(m.group(1));
        this.winningNumbers = readNumbers(m.group(2));
        this.cardNumbers = readNumbers(m.group(3));
    }

    public List<Integer> readNumbers(String numbers) {
        if (numbers != null && NumberUtils.isDigits(numbers.replaceAll(" ", ""))) {
            return Arrays.stream(numbers.split(" +")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
        }
        return List.of();
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public List<Integer> getCardNumbers() {
        return cardNumbers;
    }

    public int getPoints() {
        int matches = getMatches();
        return matches == 0 ? 0 : (int) Math.pow(2, matches - 1);
    }

    public int getId() {
        return id;
    }

    public int getNbCopies() {
        return nbCopies;
    }

    public int getMatches() {
        return CollectionUtils.intersection(winningNumbers, cardNumbers).size();
    }

    public void increaseNbCopies(int nbCopies) {
        this.nbCopies += nbCopies;
    }
}
