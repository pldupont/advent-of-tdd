package com.pld.adventoftdd.day4;

import java.util.List;
import java.util.stream.IntStream;

public class ScratchCardList {
    private final List<ScratchCard> scratchCards;

    public ScratchCardList(List<String> scratchCards) {
        this.scratchCards = scratchCards.stream().map(ScratchCard::new).toList();
    }

    public List<ScratchCard> getScratchCards() {
        return scratchCards;
    }

    public int calculateCopies() {
        IntStream.range(0, scratchCards.size())
                .forEach(this::incrementCopies);
        return scratchCards.stream().mapToInt(ScratchCard::getNbCopies).sum();
    }

    private void incrementCopies(int index) {
        if (index + 1 < scratchCards.size()) {
            int matches = scratchCards.get(index).getMatches();
            scratchCards.subList(index + 1, Math.min(scratchCards.size(), index + matches + 1))
                    .forEach(scratchCard -> scratchCard.increaseNbCopies(scratchCards.get(index).getNbCopies()));
        }
    }
}
