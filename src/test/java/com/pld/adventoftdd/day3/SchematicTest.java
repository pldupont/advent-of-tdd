package com.pld.adventoftdd.day3;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SchematicTest {

    @Test
    void givenAnEmptySchematic_whenCallingReadSchematic_thenReturnAnEmptyTableOfNodes() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = "";

        // When
        aSchematic.readSchematic(schematic);

        // Then
        assertThat(aSchematic.getNodes(), is(notNullValue(List.class)));
        assertThat(aSchematic.getNodes().size(), is(equalTo(0)));
    }

    @Test
    void givenASingleLineSchematicWith3Dot_whenCallingReadSchematic_thenReturnListOf1ListWith3Dots() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = "...";

        // When
        aSchematic.readSchematic(schematic);

        // Then
        assertThat(aSchematic.getNodes(), is(notNullValue(List.class)));
        assertThat(aSchematic.getNodes().size(), is(equalTo(1)));
        assertAll("All nodes are dots",
                () -> assertThat(aSchematic.getNodes().get(0).size(), is(equalTo(3))),
                () -> assertAll("None are digits",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isDigit(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isDigit(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isDigit(), is(false))
                ),
                () -> assertAll("None are symbols",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isSymbol(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isSymbol(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isSymbol(), is(false))
                ),
                () -> assertAll("None are kept",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isKeep(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isKeep(), is(false))
                ),
                () -> assertAll("All are dots",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).getCharacter(), is(equalTo('.'))),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('.'))),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).getCharacter(), is(equalTo('.')))
                )
        );
    }

    @Test
    void givenASingleLineSchematicWith3Digits_whenCallingReadSchematic_thenReturnListOf1ListWith3Digits() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = "123";

        // When
        aSchematic.readSchematic(schematic);

        // Then
        assertThat(aSchematic.getNodes(), is(notNullValue(List.class)));
        assertThat(aSchematic.getNodes().size(), is(equalTo(1)));
        assertAll("All nodes are dots",
                () -> assertThat(aSchematic.getNodes().get(0).size(), is(equalTo(3))),
                () -> assertAll("None are digits",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isDigit(), is(true)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isDigit(), is(true)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isDigit(), is(true))
                ),
                () -> assertAll("None are symbols",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isSymbol(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isSymbol(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isSymbol(), is(false))
                ),
                () -> assertAll("None are kept",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isKeep(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isKeep(), is(false))
                ),
                () -> assertAll("All are dots",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).getCharacter(), is(equalTo('1'))),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('2'))),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).getCharacter(), is(equalTo('3')))
                )
        );
    }

    @Test
    void givenASingleLineSchematicWith3Symbols_whenCallingReadSchematic_thenReturnListOf1ListWith3Symbols() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = "$%^";

        // When
        aSchematic.readSchematic(schematic);

        // Then
        assertThat(aSchematic.getNodes(), is(notNullValue(List.class)));
        assertThat(aSchematic.getNodes().size(), is(equalTo(1)));
        assertAll("All nodes are dots",
                () -> assertThat(aSchematic.getNodes().get(0).size(), is(equalTo(3))),
                () -> assertAll("None are digits",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isDigit(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isDigit(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isDigit(), is(false))
                ),
                () -> assertAll("None are symbols",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isSymbol(), is(true)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isSymbol(), is(true)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isSymbol(), is(true))
                ),
                () -> assertAll("None are kepts",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).isKeep(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).isKeep(), is(false))
                ),
                () -> assertAll("All are dots",
                        () -> assertThat(aSchematic.getNodes().get(0).get(0).getCharacter(), is(equalTo('$'))),
                        () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('%'))),
                        () -> assertThat(aSchematic.getNodes().get(0).get(2).getCharacter(), is(equalTo('^')))
                )
        );
    }

    @Test
    void given4LinesSchematicWith3MixedCharacters_whenCallingReadSchematic_thenReturnListOf4x3() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                1.5
                8$$
                123
                ..1""";

        // When
        aSchematic.readSchematic(schematic);

        // Then
        assertThat(aSchematic.getNodes(), is(notNullValue(List.class)));
        assertThat(aSchematic.getNodes().size(), is(equalTo(4)));
        assertAll("All nodes are dots",
                () -> assertThat(aSchematic.getNodes().get(0).size(), is(equalTo(3))),
                () -> assertThat(aSchematic.getNodes().get(1).size(), is(equalTo(3))),
                () -> assertThat(aSchematic.getNodes().get(2).size(), is(equalTo(3))),
                () -> assertThat(aSchematic.getNodes().get(3).size(), is(equalTo(3)))
        );
    }

    @Test
    void givenADigitNodeThatHasBeenFlaggedAsKeepAndNoSideDigits_whenCallingFlagSideDigits_thenNoMoreNodesAreFlaggedAsKeep() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2.
                .8.
                #.1
                ..#""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.flagToKeep(1, 1, null);

        // Then
        assertAll("Check that only the digit flagged is kept",
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getCharacter(), is(equalTo('8'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(2).get(2).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(2).get(2).isKeep(), is(false))
        );
    }

    @Test
    void givenADigitNodeThatHasBeenFlaggedAsKeepAndWithBackAndFrontDigits_whenCallingFlagSideDigits_thenSideDigitsAreFlaggedAsWell() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2...3
                1823.1
                #.....""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.flagToKeep(1, 1, null);

        // Then
        assertAll("Check that only the digit flagged is kept",
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).getCharacter(), is(equalTo('3'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getCharacter(), is(equalTo('8'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).getCharacter(), is(equalTo('3'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).getCharacter(), is(equalTo('.'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).isKeep(), is(false))
        );
    }

    @Test
    void givenADigitNodeThatHasBeenFlaggedAsKeepAndWithDigitsAlreadyFlagged_whenCallingFlagSideDigits_thenSameDigitsAreFlagged() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2...3
                1823.1
                #.....""";
        aSchematic.readSchematic(schematic);
        aSchematic.flagToKeep(1, 0, null);

        // When
        aSchematic.flagToKeep(1, 1, null);

        // Then
        assertAll("Check that only the digit flagged is kept",
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).getCharacter(), is(equalTo('3'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getCharacter(), is(equalTo('8'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).getCharacter(), is(equalTo('3'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).getCharacter(), is(equalTo('.'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).isKeep(), is(false))
        );
    }

    @Test
    void givenASchematicLoaded_whenCallingFlagDigitsAroundSymbol_thenAllNodesAroundTheSymbolAndSideDigitsAreFlagged() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2...3
                1823.1
                #.....""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.flagDigitsAround(2, 0);

        // Then
        assertAll("Check that only the digit flagged is kept",
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).getCharacter(), is(equalTo('3'))),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getCharacter(), is(equalTo('8'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).getCharacter(), is(equalTo('2'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).getCharacter(), is(equalTo('3'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).getCharacter(), is(equalTo('.'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).getCharacter(), is(equalTo('1'))),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).isKeep(), is(false))
        );
    }

    @Test
    void givenASchematicLoaded_whenCallingMarkDigitsToKeep_thenAllNodesAdjacentToASymbolsOrANumberFlaggedIsKept() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                $2...%
                1..3..
                *9.21#""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.markDigitsToKeep();

        // Then
        assertAll("Check that only the digit flagged is kept",
                () -> assertThat(aSchematic.getNodes().get(0).get(0).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(0).get(2).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(3).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(4).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(2).get(0).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(2).get(1).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(2).get(2).isKeep(), is(false)),
                () -> assertThat(aSchematic.getNodes().get(2).get(3).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(2).get(4).isKeep(), is(true)),
                () -> assertThat(aSchematic.getNodes().get(2).get(5).isKeep(), is(false))
        );
    }

    @Test
    void givenASchematicLoadedAndMarked_whenCallingListValidNumbers_thenReturnAListWith2_1_4_9_21() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                $2...%
                1..3.4
                *9.21#""";
        aSchematic.readSchematic(schematic);
        aSchematic.markDigitsToKeep();

        // When
        List<Integer> result = aSchematic.listValidNumbers();

        // Then
        assertIterableEquals(result, List.of(2, 1, 4, 9, 21));
    }

    @Test
    void givenASchematicLoadedWithExample_whenCallingListValidNumbers_thenTheSumOfTheListIs4361() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..""";
        aSchematic.readSchematic(schematic);
        aSchematic.markDigitsToKeep();

        // When
        List<Integer> result = aSchematic.listValidNumbers();

        // Then
        assertThat(result.stream().mapToInt(Integer::intValue).sum(), is(equalTo(4361)));
    }

    @Test
    void givenTheDay3Input() throws Exception {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = FileUtils.readFileToString(new File(Thread.currentThread().getContextClassLoader().getResource("day3-input.txt").toURI()), Charset.defaultCharset());
        aSchematic.readSchematic(schematic);
        aSchematic.markDigitsToKeep();

        // When
        List<Integer> result = aSchematic.listValidNumbers();

        // Then
        assertThat(result.stream().mapToInt(Integer::intValue).sum(), is(equalTo(530849)));
    }

    @Test
    void givenTheSymbolIsAGear_whenCallingFlagDigitsAround_thenAddGearNodeToEachDigitAround() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2.**3
                1823.1
                #.....""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.flagDigitsAround(0, 4);

        // Then
        assertAll("Check that only the digit around the gear has a reference.",
                () -> assertThat(aSchematic.getNodes().get(0).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(2).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(5).getGearNodes().size(), is(equalTo(0)))
        );
    }

    @Test
    void givenTheSymbolIsNotAGear_whenCallingFlagDigitsAround_thenAddGearNodeToEachDigitAround() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2.**3
                1823.1
                #.....""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.flagDigitsAround(2, 0);

        // Then
        assertAll("Check that only the digit around the gear has a reference.",
                () -> assertThat(aSchematic.getNodes().get(0).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(5).getGearNodes().size(), is(equalTo(0)))
        );
    }

    @Test
    void givenSomeGears_whenCallingMarkDigitsToKeep_thenAddGearNodeToEachDigitAround() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2.**3
                1823.1
                *.....""";
        aSchematic.readSchematic(schematic);

        // When
        aSchematic.markDigitsToKeep();

        // Then
        assertAll("Check that only the digit around the gear has a reference.",
                () -> assertThat(aSchematic.getNodes().get(0).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(0).get(5).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(1).get(0).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(1).get(1).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(1).get(2).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(1).get(3).getGearNodes().size(), is(equalTo(2))),
                () -> assertThat(aSchematic.getNodes().get(1).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(1).get(5).getGearNodes().size(), is(equalTo(1))),
                () -> assertThat(aSchematic.getNodes().get(2).get(0).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(1).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(2).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(3).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(4).getGearNodes().size(), is(equalTo(0))),
                () -> assertThat(aSchematic.getNodes().get(2).get(5).getGearNodes().size(), is(equalTo(0)))
        );
    }

    @Test
    void givenSomeGears_whenCallingListValidNumbers_thenGearsAreUpdatedWithAssociatedNumber() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                .2.**3
                1823.1
                *.....""";
        aSchematic.readSchematic(schematic);
        aSchematic.markDigitsToKeep();

        // When
        aSchematic.listValidNumbers();

        // Then
        assertAll("Check that only the digit around the gear has a reference.",
                () -> assertIterableEquals(aSchematic.getNodes().get(0).get(3).getGearNumbers(), List.of(1823)),
                () -> assertIterableEquals(aSchematic.getNodes().get(0).get(4).getGearNumbers(), List.of(3, 1823, 1)),
                () -> assertIterableEquals(aSchematic.getNodes().get(2).get(0).getGearNumbers(), List.of(1823))
        );
    }


    @Test
    void givenASchematicLoadedWithExample_whenCallingListValidNumbers_thenTheSumOfGearRatiosIs4361() {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..""";
        aSchematic.readSchematic(schematic);
        aSchematic.markDigitsToKeep();
        aSchematic.listValidNumbers();

        // When
        List<List<Node>> result = aSchematic.getNodes();

        // Then
        assertThat(result.stream().flatMap(List::stream).mapToInt(Node::getGearRatio).sum(), is(equalTo(467835)));
    }

    @Test
    void givenTheDay3Input_part2() throws Exception {
        // Given
        Schematic aSchematic = new Schematic();
        String schematic = FileUtils.readFileToString(new File(Thread.currentThread().getContextClassLoader().getResource("day3-input.txt").toURI()), Charset.defaultCharset());
        aSchematic.readSchematic(schematic);
        aSchematic.markDigitsToKeep();
        aSchematic.listValidNumbers();

        // When
        List<List<Node>> result = aSchematic.getNodes();

        // Then
        assertThat(result.stream().flatMap(List::stream).mapToInt(Node::getGearRatio).sum(), is(equalTo(84900879)));
    }
}