package com.pld.adventoftdd.day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class GameResultsTest {

    @Test
    void givenAMissingResultFile_whenCallingLoadResults_thenThrowsIOException() {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "missingFile.txt";

        // When
        IOException result = assertThrowsExactly(IOException.class, () -> gameResults.loadResults(aFilename));

        // Then
        assertThat(result.getMessage(), equalTo("Results file 'missingFile.txt' doesn't exist."));
    }

    @Test
    void givenAnEmptyResultFile_whenCallingLoadResults_thenGameListIsEmpty() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "emptyFile.txt";

        // When
        gameResults.loadResults(aFilename);

        // Then
        assertThat(gameResults.getGames(), is(notNullValue(List.class)));
        assertThat(gameResults.getGames().size(), is(equalTo(0)));
    }

    @Test
    void givenDay2Part1ExampleResultFile_whenCallingLoadResults_thenGameListContains5Games() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "example-day2-part1.txt";

        // When
        gameResults.loadResults(aFilename);

        // Then
        assertThat(gameResults.getGames(), is(notNullValue(List.class)));
        assertThat(gameResults.getGames().size(), is(equalTo(5)));
    }

    @Test
    void givenDay2Part1InputResultFile_whenCallingLoadResults_thenGameListContains100Games() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "day2-input.txt";

        // When
        gameResults.loadResults(aFilename);

        // Then
        assertThat(gameResults.getGames(), is(notNullValue(List.class)));
        assertThat(gameResults.getGames().size(), is(equalTo(100)));
    }

    @Test
    void givenAnEmptyResultFileLoaded_whenCallingGetMatchingGames_thenReturnAnEmptyList() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "emptyFile.txt";
        gameResults.loadResults(aFilename);

        // When
        List<Game> results = gameResults.getMatchingGames(12, 13, 14);

        // Then
        assertThat(results, is(notNullValue(List.class)));
        assertThat(results.size(), is(equalTo(0)));
    }

    @Test
    void givenDay2Part1ExampleResultFileLoaded_whenCallingMatchingGames_thenReturnAListOf3Games() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "example-day2-part1.txt";
        gameResults.loadResults(aFilename);

        // When
        List<Game> results = gameResults.getMatchingGames(12, 13, 14);

        // Then
        assertThat(results, is(notNullValue(List.class)));
        assertThat(results.size(), is(equalTo(3)));
    }

    @Test
    void givenDay2Part1ExampleResultFileLoaded_whenCallingMatchingGamesSumOfIds_thenReturn8() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "example-day2-part1.txt";
        gameResults.loadResults(aFilename);

        // When
        int result = gameResults.getMatchingGamesSumOfIds(12, 13, 14);

        // Then
        assertThat(result, is(equalTo(8)));
    }

    @Test
    void givenDay2Part1InputResultFileLoaded_whenCallingLoadResultsSumOfIds_thenReturnResponse() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "day2-input.txt";
        gameResults.loadResults(aFilename);

        // When
        int result = gameResults.getMatchingGamesSumOfIds(12, 13, 14);

        // Then
        assertThat(result, is(equalTo(2369)));
    }

    @Test
    void givenDay2Part1ExampleResultFileLoaded_whenCallingMatchingGamesSumMinimalPower_thenReturn2286() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "example-day2-part1.txt";
        gameResults.loadResults(aFilename);

        // When
        int result = gameResults.getMatchingGamesSumOfMinimalPower();

        // Then
        assertThat(result, is(equalTo(2286)));
    }

    @Test
    void givenDay2Part1InputResultFileLoaded_whenCallingLoadResultsSumMinimalPower_thenReturnResponse() throws IOException, URISyntaxException {
        // Given
        GameResults gameResults = new GameResults();
        String aFilename = "day2-input.txt";
        gameResults.loadResults(aFilename);

        // When
        int result = gameResults.getMatchingGamesSumOfMinimalPower();

        // Then
        assertThat(result, is(equalTo(66363)));
    }
}