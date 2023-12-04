package com.pld.adventoftdd.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalibrationDocumentReaderTest {

    @Test
    void givenAnEmptyLine_whenCallingReadLine_thenReturn0() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String anEmptyLine = "";

        // When
        int result = instance.readLine(anEmptyLine);

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void givenALineWithLettersOnly_whenCallingReadLine_thenReturn0() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String aLineWithLettersOnly = "LettersOnly";

        // When
        int result = instance.readLine(aLineWithLettersOnly);

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void givenALineEqualsTo5_whenCallingReadLine_thenReturn55() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String aLine = "5";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(55));
    }

    @Test
    void givenALineEqualsTo5302_whenCallingReadLine_thenReturn52() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String aLine = "5302";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(52));
    }

    @Test
    void givenALineEqualsToAB5s3E0as2Z_whenCallingReadLine_thenReturn52() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String aLine = "AB5s3E0as2Z";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(52));
    }

    @Test
    void givenALineEqualsToAB3Z_whenCallingReadLine_thenReturn33() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String aLine = "AB3Z";

        // When
        int result = instance.readLine(aLine);

        // Then
        assertThat(result, equalTo(33));
    }

    @Test
    void givenAnEmptyFile_whenCallingProcessDocument_thenReturn0() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String anEmptyFile = "emptyFile.txt";

        // When
        int result = instance.processDocument(anEmptyFile);

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void givenTheExampleFile_whenCallingProcessDocument_thenReturn142() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String anEmptyFile = "example-day1-part1.txt";

        // When
        int result = instance.processDocument(anEmptyFile);

        // Then
        assertThat(result, equalTo(142));
    }

    @Test
    void givenAMissingFile_whenCallingProcessDocument_thenReturn142() {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String aMissingFile = "aMissingFile.txt";

        // When
        IOException result = Assertions.assertThrows(IOException.class, () -> instance.processDocument(aMissingFile));

        // Then
        assertThat(result.getMessage(), equalTo("Document 'aMissingFile.txt' doesn't exist."));
    }

    @Test
    void givenDay1Input_whenCallingProcessDocument_thenReturnResult() throws URISyntaxException, IOException {
        // Given
        CalibrationDocumentReader instance = new CalibrationDocumentReader();
        String day1Input = "day1-input.txt";

        // When
        int result = instance.processDocument(day1Input);

        // Then
        assertThat(result, equalTo(54708));
    }
}
