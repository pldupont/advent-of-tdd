package org.advent.day1;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class ElfNotesReader {
    private List<Elf> elves = new ArrayList<>();

    public List<Elf> getElves() {
        return elves;
    }

    public void readNotes(String notes) throws IOException, URISyntaxException {
        try (Stream<String> lines = Files.lines(new File(Thread.currentThread().getContextClassLoader().getResource(notes).toURI()).toPath())) {
            lines.forEach(this::addCalories);
        }
    }

    private void addCalories(String caloriesAsString) {
        if (caloriesAsString.isEmpty()) {
            elves.add(new Elf());
        } else {
            if (elves.isEmpty()) {
                elves.add(new Elf());
            }
            elves.get(elves.size() - 1).addCalories(Integer.valueOf(caloriesAsString));
        }
    }

    public Optional<Elf> findElfWithMostCalories() {
        return elves.stream().sorted((elf1, elf2) -> elf2.compareTo(elf1)).findFirst();
    }

   
}