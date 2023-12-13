package com.pld.adventoftdd.day3;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final char character;
    private final boolean symbol;
    private final boolean digit;
    private boolean keep = false;
    private final boolean gear;
    private final List<Node> gearNodes = new ArrayList<>();
    private final List<Integer> gearNumbers = new ArrayList<>();

    public Node(char character) {
        this.character = character;
        this.digit = NumberUtils.isDigits(Character.toString(character));
        this.symbol = !digit && character != '.';
        this.gear = character == '*';
    }

    public char getCharacter() {
        return character;
    }

    public boolean isDigit() {
        return digit;
    }

    public boolean isSymbol() {
        return symbol;
    }

    public boolean isKeep() {
        return keep && isDigit();
    }

    public void keep() {
        this.keep = true;
    }

    public boolean isGear() {
        return gear;
    }

    public void addGearNode(Node node) {
        if (isDigit() && node != null && node.isGear()) {
            this.gearNodes.add(node);
        }
    }

    public List<Node> getGearNodes() {
        return gearNodes;
    }

    public void addGearNumber(int gearNumber) {
        if (isGear()) {
            this.gearNumbers.add(gearNumber);
        }
    }

    public List<Integer> getGearNumbers() {
        return gearNumbers;
    }

    public int getGearRatio() {
        if (gearNumbers.size() == 2) {
            return gearNumbers.get(0) * gearNumbers.get(1);
        }
        return 0;
    }
}
