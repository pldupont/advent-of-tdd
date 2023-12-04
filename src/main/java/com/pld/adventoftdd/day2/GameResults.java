package com.pld.adventoftdd.day2;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameResults {
    private final List<Game> games = new ArrayList<>();

    public void loadResults(String resultsFile) throws IOException, URISyntaxException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resultsFile);
        if (url == null) {
            throw new IOException(String.format("Results file '%s' doesn't exist.", resultsFile));
        }
        try (Stream<String> stream = Files.lines(new File(url.toURI()).toPath())) {
            stream.map(Game::readGameLine).forEach(this.games::add);
        }
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Game> getMatchingGames(int countRed, int countGreen, int countBlue) {
        return games.stream().filter(g -> isValidGame(g, countRed, countGreen, countBlue)).toList();
    }

    private boolean isValidGame(Game game, int countRed, int countGreen, int countBlue) {
        return game.getMax("red") <= countRed && game.getMax("green") <= countGreen && game.getMax("blue") <= countBlue;
    }

    public int getMatchingGamesSumOfIds(int countRed, int countGreen, int countBlue) {
        return getMatchingGames(countRed, countGreen, countBlue).stream().mapToInt(Game::getId).sum();
    }

    public int getMatchingGamesSumOfMinimalPower() {
        return games.stream().mapToInt(Game::getMinimalPower).sum();
    }
}
