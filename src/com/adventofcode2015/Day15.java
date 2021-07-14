package com.adventofcode2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day15 {

    static String input_test = """
            Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
            Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
            """;

    static String input = """
            Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2
            Sprinkles: capacity -3, durability 3, flavor 0, texture 0, calories 9
            Candy: capacity -1, durability 0, flavor 4, texture 0, calories 1
            Chocolate: capacity 0, durability 0, flavor -2, texture 2, calories 8""";

    final static int nbOfSpoons = 100;

    public static void main(String[] args) {

        class Ingredient {
            public final String name;
            public final short capacity;
            public final short durability;
            public final short flavor;
            public final short texture;
            public final short calories;

            public Ingredient(String name, short capacity, short durability, short flavor, short texture, short calories) {
                this.name = name;
                this.capacity = capacity;
                this.durability = durability;
                this.flavor = flavor;
                this.texture = texture;
                this.calories = calories;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("Ingredient{");
                sb.append("name='").append(name).append('\'');
                sb.append(", capacity=").append(capacity);
                sb.append(", durability=").append(durability);
                sb.append(", flavor=").append(flavor);
                sb.append(", texture=").append(texture);
                sb.append(", calories=").append(calories);
                sb.append('}');
                return sb.toString();
            }
        }

        List<Ingredient> ingredients = new ArrayList<>();
        for(String line : input.split("\n")) {
            String[] words = line.split(" ");
            ingredients.add(new Ingredient(
                    words[0].replace(":",""),
                    Short.parseShort(words[2].replace(",","")),
                    Short.parseShort(words[4].replace(",","")),
                    Short.parseShort(words[6].replace(",","")),
                    Short.parseShort(words[8].replace(",","")),
                    Short.parseShort(words[10])
            ));
        }

        List<List<Integer>> combinations = new ArrayList<>();
        spoonCombinations(ingredients.size(), 100, new ArrayList<>(), combinations);

        List<Integer> winningRecipePart1 = null;
        List<Integer> winningRecipePart2 = null;
        int lastSumPart1 = 0;
        int lastSumPart2 = 0;

        for (List<Integer> spoonCombination : combinations){
            int sum = Math.max(0, ingredients.stream().mapToInt(ingredient -> ingredient.capacity * spoonCombination.get(ingredients.indexOf(ingredient))).sum()) *
                    Math.max(0, ingredients.stream().mapToInt(ingredient -> ingredient.durability * spoonCombination.get(ingredients.indexOf(ingredient))).sum()) *
                    Math.max(0, ingredients.stream().mapToInt(ingredient -> ingredient.flavor * spoonCombination.get(ingredients.indexOf(ingredient))).sum()) *
                    Math.max(0, ingredients.stream().mapToInt(ingredient -> ingredient.texture * spoonCombination.get(ingredients.indexOf(ingredient))).sum());
            int calories = ingredients.stream().mapToInt(ingredient -> ingredient.calories * spoonCombination.get(ingredients.indexOf(ingredient))).sum();
            if(sum > lastSumPart1){
                lastSumPart1 = sum;
                winningRecipePart1 = spoonCombination;
            }
            if(calories == 500 && sum > lastSumPart2){
                lastSumPart2 = sum;
                winningRecipePart2 = spoonCombination;
            }
        }

        System.out.println("The highest-scoring cookie for part 1 scores " + lastSumPart1);
        System.out.println("The winning-recipe for part 1 is " + winningRecipePart1);

        System.out.println("The highest-scoring cookie for part 2 scores " + lastSumPart2);
        System.out.println("The winning-recipe for part 2 is " + winningRecipePart2);

    }

    static void spoonCombinations(int size, int maxSum, ArrayList<Integer> previousList, List<List<Integer>> combinations){
        ArrayList<Integer> tmpList = (ArrayList<Integer>) previousList.clone();

        if(size == 1){
            tmpList.add(maxSum);
            combinations.add(tmpList);
        }
        else {
            for (int i = 0; i < maxSum; i++) {
                tmpList = (ArrayList<Integer>) previousList.clone();
                tmpList.add(i);
                spoonCombinations(size - 1, maxSum - i, tmpList, combinations);
            }
        }
    }
}
