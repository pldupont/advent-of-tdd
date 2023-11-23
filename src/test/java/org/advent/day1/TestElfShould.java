package org.advent.day1;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestElfShould {
    @Test
    void have_zero_total_calories_when_created() {
        // Given
        Elf anElf = new Elf();

        // When
        int result = anElf.getTotalCalories();

        // Then
        assertThat(result, equalTo(0));
    }

    @Test
    void have_1000_total_calories_after_adding_1000() {
        // Given
        Elf anElf = new Elf();

        // When
        anElf.addCalories(1000);
        int result = anElf.getTotalCalories();

        // Then
        assertThat(result, equalTo(1000));

    }

    @Test
    void compare_return_positive_value_for_elf_with_more_total_calories() {
        // Given
        Elf anElf = new Elf();
        anElf.addCalories(500);
        Elf anotherElfWithMoreCalories = new Elf();
        anotherElfWithMoreCalories.addCalories(1000);

        // When
        int result = anotherElfWithMoreCalories.compareTo(anElf);

        // Then
        assertThat(result, greaterThan(0));
    }

    @Test
    void compare_zero_for_equal_elf() {
        // Given
        Elf anElf = new Elf();
        anElf.addCalories(500);
        Elf anotherElfWithSameCalories = new Elf();
        anotherElfWithSameCalories.addCalories(500);

        // When
        int result = anElf.compareTo(anotherElfWithSameCalories);

        // Then
        assertThat(result, equalTo(0));
       }

    @Test
    void compare_return_negative_value_for_elf_with_less_total_calories() {
        // Given
        Elf anElf = new Elf();
        anElf.addCalories(500);
        Elf anotherElfWithMoreCalories = new Elf();
        anotherElfWithMoreCalories.addCalories(1000);

        // When
        int result = anElf.compareTo(anotherElfWithMoreCalories);

        // Then
        assertThat(result, lessThan(0));
    }
}
