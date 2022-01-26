package com.adventofcode2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 {

    static String input = """
            33
            14
            18
            20
            45
            35
            16
            35
            1
            13
            18
            13
            50
            44
            48
            6
            24
            41
            30
            42""";

    static String input_test = """
            20
            15
            10
            5
            5""";

    static Integer eggnogQuantity = 150;
    static Integer eggnogQuantity_test = 25;
    static List<List<Integer>> combinationsList = new ArrayList<>();
    static ArrayList<Integer> containers = new ArrayList<>();
    public static void main(String[] args) {
        containers = (ArrayList<Integer>) Arrays.stream(input_test.split("\n")).map(Integer::parseInt).collect(Collectors.toList());
        Integer combinations = computeContainersList(containers, eggnogQuantity_test, 0);
        System.out.println("There are " + combinations + " different combinations of containers");
    }

    public static Integer computeContainersList(ArrayList<Integer> containersLeft, Integer quantityLeft, Integer nbOfCombinations){
        if(quantityLeft == 0){
            ArrayList<Integer> containersCopy = (ArrayList<Integer>) containersLeft.clone();
            for (Integer c : containers){
                containersCopy.remove(c);
            }
            combinationsList.add(containersCopy);
            System.out.println(containersCopy);
            return nbOfCombinations+1;
        }
        else if(quantityLeft < 0 ||
                containersLeft.stream().noneMatch(containerCapacity -> containerCapacity <= quantityLeft)){
            return -1;
        }
        for (Integer container : containersLeft) {
//                containersLeft.stream().filter(containerCapacity -> containerCapacity <= quantityLeft).collect(Collectors.toList())){
            //Vire le container
            ArrayList<Integer> tmpContainersList = (ArrayList<Integer>) containersLeft.clone();
            tmpContainersList.remove(container);
            nbOfCombinations = computeContainersList(tmpContainersList, quantityLeft - container, nbOfCombinations) ;
        }
        return nbOfCombinations;
    }
}
