package org.advent.day1;

/**
 * Elf that is assigned calories
 */
public class Elf implements Comparable<Elf> {

    public static void main(String[] args) {
        System.out.println(Integer.valueOf(1).compareTo(Integer.valueOf(0)));
    }

    private int totalCalories = 0;

    /**
     * Calories carried by the Elf
     * @return total calories
     */
    public int getTotalCalories() {
        return totalCalories;
    }

    @Override
    public int compareTo(Elf anotherElf) {
        return ((Integer) this.totalCalories).compareTo(anotherElf.getTotalCalories());
    }

    public void addCalories(int calories) {
        this.totalCalories += calories;
    }

}
