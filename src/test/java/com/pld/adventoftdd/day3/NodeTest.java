package com.pld.adventoftdd.day3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class NodeTest {

    @Test
    void givenADot_whenCreatingANode_thenNodeContainsADotAndValidIsFalse() {
        // Given
        char aCharacter = '.';

        // When
        Node result = new Node(aCharacter);

        // Then
        assertAll("Character is a digit",

                () -> assertThat(result.getCharacter(), is(equalTo('.'))),
                () -> assertThat(result.isSymbol(), is(false)),
                () -> assertThat(result.isDigit(), is(false)),
                () -> assertThat(result.isGear(), is(false)),
                () -> assertThat(result.isKeep(), is(false))
        );
    }

    @Test
    void givenADigit_whenCreatingANode_thenNodeContainsADigitAndValidIsFalse() {
        // Given
        char aCharacter = '5';

        // When
        Node result = new Node(aCharacter);

        // Then
        assertAll("Character is a digit",

                () -> assertThat(result.getCharacter(), is(equalTo('5'))),
                () -> assertThat(result.isSymbol(), is(false)),
                () -> assertThat(result.isDigit(), is(true)),
                () -> assertThat(result.isGear(), is(false)),
                () -> assertThat(result.isKeep(), is(false))
        );
    }

    @Test
    void givenAnyOtherCharThenDotOrDigit_whenCreatingANode_thenNodeContainsADigitAndValidIsTrue() {
        // Given
        char aCharacter = '#';

        // When
        Node result = new Node(aCharacter);

        // Then
        assertAll("Character is a symbol",

                () -> assertThat(result.getCharacter(), is(equalTo('#'))),
                () -> assertThat(result.isSymbol(), is(true)),
                () -> assertThat(result.isDigit(), is(false)),
                () -> assertThat(result.isGear(), is(false)),
                () -> assertThat(result.isKeep(), is(false))
        );
    }

    @Test
    void givenADigitNode_whenCallingKeep_thenIsKeepReturnTrue() {
        // Given
        Node aNode = new Node('5');

        // When
        aNode.keep();

        // Then
        assertThat(aNode.isKeep(), is(true));
    }

    @Test
    void givenASymbolNode_whenCallingKeep_thenIsKeepReturnFalse() {
        // Given
        Node aNode = new Node('#');

        // When
        aNode.keep();

        // Then
        assertThat(aNode.isKeep(), is(false));
    }

    @Test
    void givenADotNode_whenCallingKeep_thenIsKeepReturnFalse() {
        // Given
        Node aNode = new Node('.');

        // When
        aNode.keep();

        // Then
        assertThat(aNode.isKeep(), is(false));
    }

    @Test
    void givenAStar_whenCreatingANode_thenNodeContainsADotAndGearIsTrueAndSymbolIsTrue() {
        // Given
        char aCharacter = '*';

        // When
        Node result = new Node(aCharacter);

        // Then
        assertAll("Character is a digit",

                () -> assertThat(result.getCharacter(), is(equalTo('*'))),
                () -> assertThat(result.isSymbol(), is(true)),
                () -> assertThat(result.isDigit(), is(false)),
                () -> assertThat(result.isGear(), is(true)),
                () -> assertThat(result.isKeep(), is(false))
        );
    }

    @Test
    void givenAGearNodeAndADigitNode_whenCallingAddGearNodeOnTheDigitWithTheGear_thenNodeAddedToTheList() {
        // Given
        Node aGear = new Node('*');
        Node aDigit = new Node('1');

        // When
        aDigit.addGearNode(aGear);

        // Then
        assertThat(aDigit.getGearNodes().get(0), is(equalTo(aGear)));
    }

    @Test
    void given2GearNodes_whenCallingAddGearNode_thenListIsEmpty() {
        // Given
        Node aGear = new Node('*');
        Node aSecondGear = new Node('*');

        // When
        aSecondGear.addGearNode(aGear);

        // Then
        assertThat(aSecondGear.getGearNodes().size(), is(equalTo(0)));
    }

    @Test
    void given2DigitNodes_whenCallingAddGearNode_thenListIsEmpty() {
        // Given
        Node aDigit = new Node('1');
        Node aSecondDigit = new Node('2');

        // When
        aSecondDigit.addGearNode(aDigit);

        // Then
        assertThat(aSecondDigit.getGearNodes().size(), is(equalTo(0)));
    }

    @Test
    void givenAGear_whenCallingAddGearNumber_thenAddToTheListButGearRatioIsZero() {
        // Given
        Node aGear = new Node('*');

        // When
        aGear.addGearNumber(10);

        // Then
        assertIterableEquals(aGear.getGearNumbers(), List.of(10));
        assertThat(aGear.getGearRatio(), is(equalTo(0)));
    }

    @Test
    void givenAGear_whenCallingAddGearNumberTwice_thenAddToTheListAndReturnTheGearRatio() {
        // Given
        Node aGear = new Node('*');

        // When
        aGear.addGearNumber(10);
        aGear.addGearNumber(15);

        // Then
        assertIterableEquals(aGear.getGearNumbers(), Arrays.asList(10, 15));
        assertThat(aGear.getGearRatio(), is(equalTo(150)));
    }

    @Test
    void givenAGear_whenCallingAddGearNumberMultipleTimes_thenAddAllNumbersToTheListButGearRatioIsZero() {
        // Given
        Node aGear = new Node('*');

        // When
        aGear.addGearNumber(10);
        aGear.addGearNumber(11);
        aGear.addGearNumber(12);

        // Then
        assertIterableEquals(aGear.getGearNumbers(), Arrays.asList(10, 11, 12));
        assertThat(aGear.getGearRatio(), is(equalTo(0)));
    }

    @Test
    void givenADigit_whenCallingAddGearNumberMultipleTimes_thenListIsEmptyAndGearRatioIsZero() {
        // Given
        Node aDigit = new Node('1');

        // When
        aDigit.addGearNumber(10);
        aDigit.addGearNumber(11);
        aDigit.addGearNumber(12);

        // Then
        assertIterableEquals(aDigit.getGearNumbers(), List.of());
        assertThat(aDigit.getGearRatio(), is(equalTo(0)));
    }
}
