package com.pld.adventoftdd.day4;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ScratchCardTest {

    @Test
    void givenAString1_2_3_whenCallingReadNumbers_thenReturnAListOfIntegersMatching() {
        // Given
        ScratchCard aScratchCard = new ScratchCard();
        String aStringOfNumbers = "1 2 3";

        // When
        List<Integer> result = aScratchCard.readNumbers(aStringOfNumbers);

        // Then
        assertIterableEquals(result, List.of(1, 2, 3));
    }

    @Test
    void givenAnEmptyString_whenCallingReadNumbers_thenReturnAnEmptyList() {
        // Given
        ScratchCard aScratchCard = new ScratchCard();
        String anEmptyString = "";

        // When
        List<Integer> result = aScratchCard.readNumbers(anEmptyString);

        // Then
        assertIterableEquals(result, List.of());
    }

    @Test
    void givenAScratchCardString_whenCallingConstructor_thenNumbersAndWinningNumbersArePopulated() {
        // Given
        String aScratchCardString = "Card 5: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        // When
        ScratchCard result = new ScratchCard(aScratchCardString);

        // Then
        assertThat(result.getId(), is(equalTo(5)));
        assertThat(result.getNbCopies(), is(equalTo(1)));
        assertIterableEquals(result.getWinningNumbers(), List.of(41, 48, 83, 86, 17));
        assertIterableEquals(result.getCardNumbers(), List.of(83, 86, 6, 31, 17, 9, 48, 53));
    }

    @Test
    void givenAScratchCardString_whenCallingGetPoints_thenReturnTheCardPoint() {
        // Given
        String aScratchCardString = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        // When
        ScratchCard result = new ScratchCard(aScratchCardString);

        // Then
        assertThat(result.getPoints(), is(equalTo(8)));
    }

    @Test
    void givenAScratchCardString_whenCallingGetMatches_thenReturnTheCardNumberOfMatches() {
        // Given
        String aScratchCardString = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";

        // When
        ScratchCard result = new ScratchCard(aScratchCardString);

        // Then
        assertThat(result.getMatches(), is(equalTo(4)));
    }

    @Test
    void givenAScratchCardString_whenCallingIncreaseCopy_thenTheNbCopiesIs2() {
        // Given
        String aScratchCardString = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        ScratchCard scratchCard = new ScratchCard(aScratchCardString);

        // When
        scratchCard.increaseNbCopies(1);


        // Then
        assertThat(scratchCard.getNbCopies(), is(equalTo(2)));
    }

    @Test
    void givenAScratchCardString_whenCallingIncreaseCopy3Times_thenTheNbCopiesIs4() {
        // Given
        String aScratchCardString = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        ScratchCard scratchCard = new ScratchCard(aScratchCardString);

        // When
        scratchCard.increaseNbCopies(3);


        // Then
        assertThat(scratchCard.getNbCopies(), is(equalTo(4)));
    }

    @Test
    void givenDay4Part1Example_whenDoingTheSumOfThePointsOfEachCards_thenReturn13() {
        // Given
        String day4Part1Example = """
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""";

        // When
        int result = Arrays.stream(day4Part1Example.split("\n"))
                .map(String::trim)
//                .map(this::debug)
                .map(ScratchCard::new)
                .mapToInt(ScratchCard::getPoints)
                .sum();

        // Then
        assertThat(result, is(equalTo(13)));
    }

    @Test
    void givenDay4Input_whenDoingTheSumOfThePointsOfEachCards_thenReturnResult() throws IOException, URISyntaxException {
        // Given
        URL url = Thread.currentThread().getContextClassLoader().getResource("day4-input.txt");
        if (url == null) {
            throw new IOException("Document 'day4-input.txt' doesn't exist.");
        }


        // When
        int result;
        try (Stream<String> stream = Files.lines(new File(url.toURI()).toPath())) {
            result = stream
                    .map(ScratchCard::new)
                    .mapToInt(ScratchCard::getPoints)
                    .sum();
        }

        // Then
        assertThat(result, is(equalTo(21105)));
    }
//
//    private String debug(String s) {
//        System.out.println("'" + s + "'");
//        return s;
//    }
}
