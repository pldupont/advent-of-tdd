package com.pld.adventoftdd.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalibrationDocumentReaderPart2Test {

    @Test
    void givenAnEmptyLine_whenCallingReadLine_thenReturn0() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String anEmptyLine = "";

        // When
        int result = instance.readLine(anEmptyLine);

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void givenALineWithLettersOnly_whenCallingReadLine_thenReturn0() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLineWithLettersOnly = "LettersOnly";

        // When
        int result = instance.readLine(aLineWithLettersOnly);

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void givenALineEqualsTo5_whenCallingReadLine_thenReturn55() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLine = "5";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(55));
    }

    @Test
    void givenALineEqualsTo5302_whenCallingReadLine_thenReturn52() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLine = "5302";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(52));
    }

    @Test
    void givenALineEqualsToAB5s3E0as2Z_whenCallingReadLine_thenReturn52() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLine = "AB5s3E0as2Z";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(52));
    }

    @Test
    void givenALineEqualsToAB3Z_whenCallingReadLine_thenReturn33() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLine = "AB3Z";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(33));
    }

    @Test
    void givenAnEmptyFile_whenCallingProcessDocument_thenReturn0() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String anEmptyFile = "emptyFile.txt";

        // When
        int result = instance.processDocument(anEmptyFile);

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void givenTheExampleFile_whenCallingProcessDocument_thenReturn142() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String anEmptyFile = "example-day1-part1.txt";

        // When
        int result = instance.processDocument(anEmptyFile);

        // Then
        assertThat(result, equalTo(142));
    }

    @Test
    void givenAMissingFile_whenCallingProcessDocument_thenReturn142() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aMissingFile = "aMissingFile.txt";

        // When
        IOException result = Assertions.assertThrows(IOException.class, () -> instance.processDocument(aMissingFile));

        // Then
        assertThat(result.getMessage(), equalTo("Document 'aMissingFile.txt' doesn't exist."));
    }

    private static Stream<Arguments> textToDigit() {
        return Stream.of(
                Arguments.of("one", 11),
                Arguments.of("two", 22),
                Arguments.of("three", 33),
                Arguments.of("four", 44),
                Arguments.of("five", 55),
                Arguments.of("six", 66),
                Arguments.of("seven", 77),
                Arguments.of("eight", 88),
                Arguments.of("nine", 99),
                Arguments.of("zero", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("textToDigit")
    void givenALineEqualsToADigits_whenCallingReadLine_thenReturnThatDigitsTwice(String digit, int value) {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();

        // When
        int result = instance.readLine(digit);

        // Then
        assertThat(result, equalTo(value));
    }

    @Test
    void givenALineEqualsToonetwothree_whenCallingCleanAndReplaceLine_thenReturn13() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLine = "onetwothree";

        // When
        String result = instance.cleanAndReplaceLine(aLine);

        // Then
        assertThat(result, equalTo("13"));
    }

    @Test
    void givenALineEqualsTothreetwonenine_whenCallingCleanAndReplaceLine_thenReturn39() {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String aLine = "threetwonenine";

        // When
        String result = instance.cleanAndReplaceLine(aLine);

        // Then
        assertThat(result, equalTo("39"));
    }

    @Test
    void givenTheExampleFileDay1Part2_whenCallingProcessDocument_thenReturn281() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String anExampleDay1Part2File = "example-day1-part2.txt";

        // When
        int result = instance.processDocument(anExampleDay1Part2File);

        // Then
        assertThat(result, equalTo(281));
    }

    @Test
    void givenDay1Input_whenCallingProcessDocument_thenReturnResult() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReaderPart2 instance = new CalibrationDocumentReaderPart2();
        String day1Input = "day1-input.txt";

        // When
        int result = instance.processDocument(day1Input);

        // Then
        assertThat(result, equalTo(54087));
    }
}
