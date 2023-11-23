package org.advent.day1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestElfNotesReaderShould {
    
    @Test
    void have_no_elves_when_created() {
        // Given
        ElfNotesReader notesReader = new ElfNotesReader();

        // When
        List<Elf> elves = notesReader.getElves();

        // Then
        assertThat(elves, is(empty()));
    }

    @Test
    void have_the_right_number_of_elves_based_on_the_notes() throws IOException, URISyntaxException {
        // Given
        ElfNotesReader notesReader = new ElfNotesReader();

        // When
        notesReader.readNotes("day1-input.txt");
        List<Elf> elves = notesReader.getElves();

        // Then
        assertThat(elves.size(), equalTo(5));
    }

    @Test
    void have_the_right_total_of_calories_per_elves() throws IOException, URISyntaxException {
        // Given
        ElfNotesReader notesReader = new ElfNotesReader();

        // When
        notesReader.readNotes("day1-input.txt");
        List<Elf> elves = notesReader.getElves();

        // Then
        assertAll("All elves have the right amount of caloeries",
                () -> assertThat(elves.get(0).getTotalCalories(), equalTo(6000)),
                () -> assertThat(elves.get(1).getTotalCalories(), equalTo(4000)),
                () -> assertThat(elves.get(2).getTotalCalories(), equalTo(11000)),
                () -> assertThat(elves.get(3).getTotalCalories(), equalTo(24000)),
                () -> assertThat(elves.get(4).getTotalCalories(), equalTo(10000))
        );
    }

    @Test
    void find_elf_with_the_most_calories_when_notes_arent_red() {
        // Given
        ElfNotesReader notesReader = new ElfNotesReader();

        // When
        Optional<Elf> elfWithTheMostCalories = notesReader.findElfWithMostCalories();

        // Then
        assertThat(elfWithTheMostCalories.isPresent(), is(false));
    }

    @Test
    void find_elf_with_the_most_calories_when_notes_are_red() throws IOException, URISyntaxException {
        // Given
        ElfNotesReader notesReader = new ElfNotesReader();
        notesReader.readNotes("day1-input.txt");

        // When
        Optional<Elf> elfWithTheMostCalories = notesReader.findElfWithMostCalories();

        // Then
        assertThat(elfWithTheMostCalories.isPresent(), is(true));
        assertThat(elfWithTheMostCalories.get().getTotalCalories(), equalTo(24000));
    }
}
