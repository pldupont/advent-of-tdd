package com.pld.adventoftdd.day2;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {

    @Test
    void givenAnEmptyGameLine_whenReadGameLine_thenReturnNull() {
        // Given
        String aLine = "";

        // When
        Game game = Game.readGameLine(aLine);

        // Then
        assertThat(game, is(nullValue()));


    }

    @Test
    void givenAnInvalidGameLine_whenReadGameLine_thenReturnNull() {
        // Given
        String aLine = ";fdushfasnvldsd";

        // When
        Game game = Game.readGameLine(aLine);

        // Then
        assertThat(game, is(nullValue()));


    }

    @Test
    void givenAGameLineWithoutColors_whenReadGameLine_thenReturnGameWithIdAndMaxColorZeroForEachColor() {
        // Given
        String aLine = "Game 1:";

        // When
        Game game = Game.readGameLine(aLine);

        // Then
        assertThat(game, is(notNullValue()));
        assertAll("Check default game values",
                () -> assertThat(game.getId(), is(equalTo(1))),
                () -> assertThat(game.getMax("red"), is(equalTo(0))),
                () -> assertThat(game.getMax("green"), is(equalTo(0))),
                () -> assertThat(game.getMax("blue"), is(equalTo(0)))
        );
    }

    @Test
    void givenAGameLineWith1PickOfCubes_whenReadGameLine_thenReturnGameWithIdAndMaxColors() {
        // Given
        String aLine = "Game 1: 1 blue, 2 red, 3 green";

        // When
        Game game = Game.readGameLine(aLine);

        // Then
        assertThat(game, is(notNullValue()));
        assertAll("Check default game values",
                () -> assertThat(game.getId(), is(equalTo(1))),
                () -> assertThat(game.getMax("red"), is(equalTo(2))),
                () -> assertThat(game.getMax("green"), is(equalTo(3))),
                () -> assertThat(game.getMax("blue"), is(equalTo(1)))
        );
    }

    @Test
    void givenAGameLineWith3PickOfCubes_whenReadGameLine_thenReturnGameWithIdAndMaxColors() {
        // Given
        String aLine = "Game 1: 1 blue, 2 red, 3 green; 5 blue, 1 red, 9 green; 99 blue";

        // When
        Game game = Game.readGameLine(aLine);

        // Then
        assertThat(game, is(notNullValue()));
        assertAll("Check default game values",
                () -> assertThat(game.getId(), is(equalTo(1))),
                () -> assertThat(game.getMax("red"), is(equalTo(2))),
                () -> assertThat(game.getMax("green"), is(equalTo(9))),
                () -> assertThat(game.getMax("blue"), is(equalTo(99)))
        );
    }

    @Test
    void givenAGameLineWith3PickOfCubesAllRed_whenReadGameLine_thenReturnGameWithIdAndMaxColors() {
        // Given
        String aLine = "Game 1: 2 red; 1 red; 2 red";

        // When
        Game game = Game.readGameLine(aLine);

        // Then
        assertThat(game, is(notNullValue()));
        assertAll("Check default game values",
                () -> assertThat(game.getId(), is(equalTo(1))),
                () -> assertThat(game.getMax("red"), is(equalTo(2))),
                () -> assertThat(game.getMax("green"), is(equalTo(0))),
                () -> assertThat(game.getMax("blue"), is(equalTo(0)))
        );
    }

    @Test
    void givenFirstLineOfDay2Example_whenCallingGetMinimalPower_thenReturn48() {
        // Given
        String aLine = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        Game game = Game.readGameLine(aLine);

        // When
        int minimalPower = game.getMinimalPower();

        // Then
        assertThat(minimalPower, is(equalTo(48)));
    }

    @Test
    void givenLastLineOfDay2Example_whenCallingGetMinimalPower_thenReturn36() {
        // Given
        String aLine = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        Game game = Game.readGameLine(aLine);

        // When
        int minimalPower = game.getMinimalPower();

        // Then
        assertThat(minimalPower, is(equalTo(36)));
    }
}
