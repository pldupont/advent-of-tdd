package com.pld.adventoftdd.day3;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.*;
import java.util.stream.IntStream;

public class Schematic {

    private final List<List<Node>> nodes = new ArrayList<>();

    public void readSchematic(String schematic) {
        Arrays.stream(schematic.split("(\r\n|\n)"))
                .forEach(this::readLine);
    }

    private void readLine(String schematicLine) {
        if (StringUtils.isNotBlank(schematicLine)) {
            nodes.add(schematicLine.chars().mapToObj(c -> new Node((char) c)).toList());
        }
    }

    public List<List<Node>> getNodes() {
        return nodes;
    }

    public void flagToKeep(int row, int col, Node node) {
        if (row >= 0 && row < nodes.size() && col >= 0 && col < nodes.get(0).size() && nodes.get(row).get(col).isDigit()) {
            nodes.get(row).get(col).keep();
            nodes.get(row).get(col).addGearNode(node);
            flagSideDigitsToKeep(row, col, -1, -1);
            flagSideDigitsToKeep(row, col, 1, nodes.get(row).size());
        }
    }

    private void flagSideDigitsToKeep(int row, int col, int direction, int limit) {
        int newCol = col + direction;
        if (newCol != limit) {
            Node node = nodes.get(row).get(newCol);
            if (node.isDigit() && !node.isKeep()) {
                node.keep();
                flagSideDigitsToKeep(row, newCol, direction, limit);
            }
        }
    }

    public void flagDigitsAround(int row, int col) {
        IntStream.of(-1, 0, 1).forEach(y ->
                IntStream.of(-1, 0, 1).forEach(x -> flagToKeep(row + y, col + x, this.nodes.get(row).get(col)))
        );
    }

    public void markDigitsToKeep() {
        for (int row = 0; row < nodes.size(); row++) {
            for (int col = 0; col < nodes.get(0).size(); col++) {
                if (nodes.get(row).get(col).isSymbol()) {
                    flagDigitsAround(row, col);
                }
            }
        }
    }

    public List<Integer> listValidNumbers() {
        List<Integer> values = new ArrayList<>();
        for (List<Node> row : nodes) {
            StringBuilder number = new StringBuilder();
            Set<Node> gears = new HashSet<>();
            for (Node node : row) {
                if (node.isKeep()) {
                    number.append(node.getCharacter());
                    gears.addAll(node.getGearNodes());
                } else {
                    processNumber(number, values, gears);
                    number.setLength(0);
                    gears.clear();
                }
            }
            processNumber(number, values, gears);
        }

        return values;
    }

    private void processNumber(StringBuilder number, List<Integer> values, Set<Node> gears) {
        if (!number.isEmpty()) {
            int value = NumberUtils.toInt(number.toString());
            values.add(value);
            gears.forEach(gear -> gear.addGearNumber(value));
        }
    }
}
