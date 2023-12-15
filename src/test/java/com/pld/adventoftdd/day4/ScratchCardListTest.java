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

class ScratchCardListTest {

    @Test
    void givenAScratchCarList_whenInitializedWithAListOfStrings_thenReturnTheSameAmountOfScratchCard() {
        // Given
        String day4Part1Example = """
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""";
        List<String> listOfStrings = Arrays.stream(day4Part1Example.split("\n"))
                .map(String::trim).toList();

        // When
        ScratchCardList scratchCardList = new ScratchCardList(listOfStrings);

        // Then
        assertThat(scratchCardList.getScratchCards().size(), is(equalTo(6)));
    }

    @Test
    void givenAScratchCarList_whenCallingCalculateCopies_thenReturn30() {
        // Given
        String day4Part1Example = """
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""";
        List<String> listOfStrings = Arrays.stream(day4Part1Example.split("\n"))
                .map(String::trim).toList();
        ScratchCardList scratchCardList = new ScratchCardList(listOfStrings);

        // When
        int result = scratchCardList.calculateCopies();

        // Then
        assertThat(result, is(equalTo(30)));
    }


    @Test
    void givenDay4Input_whenCalculateCopies_thenReturnResult() throws IOException, URISyntaxException {
        // Given
        URL url = Thread.currentThread().getContextClassLoader().getResource("day4-input.txt");
        if (url == null) {
            throw new IOException("Document 'day4-input.txt' doesn't exist.");
        }
        ScratchCardList list;
        try (Stream<String> stream = Files.lines(new File(url.toURI()).toPath())) {
            list = new ScratchCardList(stream.toList());
        }

        // When
        int result = list.calculateCopies();

        // Then
        assertThat(result, is(equalTo(5329815)));
    }

}