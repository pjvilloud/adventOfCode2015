package com.adventofcode2015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * --- Day 9: All in a Single Night ---
 * Every year, Santa manages to deliver all of his presents in a single night.
 *
 * This year, however, he has some new locations to visit; his elves have provided him the distances between every pair of locations. He can start and end at any two (different) locations he wants, but he must visit each location exactly once. What is the shortest distance he can travel to achieve this?
 *
 * For example, given the following distances:
 *
 * London to Dublin = 464
 * London to Belfast = 518
 * Dublin to Belfast = 141
 * The possible routes are therefore:
 *
 * Dublin -> London -> Belfast = 982
 * London -> Dublin -> Belfast = 605
 * London -> Belfast -> Dublin = 659
 * Dublin -> Belfast -> London = 659
 * Belfast -> Dublin -> London = 605
 * Belfast -> London -> Dublin = 982
 * The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this example.
 *
 * What is the distance of the shortest route?
 *
 * Your puzzle answer was 141.
 *
 * --- Part Two ---
 * The next year, just to show off, Santa decides to take the route with the longest distance instead.
 *
 * He can still start and end at any two (different) locations he wants, and he still must visit each location exactly once.
 *
 * For example, given the distances above, the longest route would be 982 via (for example) Dublin -> London -> Belfast.
 *
 * What is the distance of the longest route?
 *
 * Your puzzle answer was 736.
 */
public class Day09 {

    public static void main(String[] args){
        String input = """
                AlphaCentauri to Snowdin = 66
                AlphaCentauri to Tambi = 28
                AlphaCentauri to Faerun = 60
                AlphaCentauri to Norrath = 34
                AlphaCentauri to Straylight = 34
                AlphaCentauri to Tristram = 3
                AlphaCentauri to Arbre = 108
                Snowdin to Tambi = 22
                Snowdin to Faerun = 12
                Snowdin to Norrath = 91
                Snowdin to Straylight = 121
                Snowdin to Tristram = 111
                Snowdin to Arbre = 71
                Tambi to Faerun = 39
                Tambi to Norrath = 113
                Tambi to Straylight = 130
                Tambi to Tristram = 35
                Tambi to Arbre = 40
                Faerun to Norrath = 63
                Faerun to Straylight = 21
                Faerun to Tristram = 57
                Faerun to Arbre = 83
                Norrath to Straylight = 9
                Norrath to Tristram = 50
                Norrath to Arbre = 60
                Straylight to Tristram = 27
                Straylight to Arbre = 81
                Tristram to Arbre = 90""";

        String input_test = """
                            London to Dublin = 464
                            London to Belfast = 518
                            Dublin to Belfast = 141
                            """;

        class Route {
            String from;
            String to;
            Integer distance;

            public Route(String from, String to, Integer distance) {
                this.from = from;
                this.to = to;
                this.distance = distance;
            }

            @Override
            public String toString() {
                return "Route{" +
                        "from='" + from + '\'' +
                        ", to='" + to + '\'' +
                        ", distance=" + distance +
                        '}';
            }
        }


        Set<String> locations = new HashSet<>();
        List<Route> routes = new ArrayList<>();

        input.lines().forEach(l -> {
            String[] splitLine = l.split(" ");
            String from = splitLine[0];
            String to = splitLine[2];
            Integer distance = Integer.parseInt(splitLine[4]);
            locations.add(from);
            locations.add(to);

            routes.add(new Route(from, to, distance));
        });

        //Find all routes
        List<String[]> circuits = new ArrayList<>();
        String[] locationsArray = locations.toArray(String[]::new);
        int[] indexes = new int[locationsArray.length];
        for (int i = 0; i < locationsArray.length; i++) {
            indexes[i] = 0;
        }
        int i = 0;
        while (i < locations.size()) {
            if (indexes[i] < i) {
                swap(locationsArray, i % 2 == 0 ?  0: indexes[i], i);
                circuits.add(locationsArray.clone());
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 0;
                i++;
            }
        }

        Integer minDistance = Integer.MAX_VALUE;
        Integer maxDistance = Integer.MIN_VALUE;
        for (String[] c :circuits) {
            Integer tmpDistance = 0;
            for (int j = 0; j < c.length - 1; j++) {
                final int pos = j;
                tmpDistance += routes.stream()
                        .filter(route -> route.from.equals(c[pos]) && route.to.equals(c[pos+1])
                                || route.from.equals(c[pos+1]) && route.to.equals(c[pos]))
                        .findFirst().get().distance;
            }
            minDistance  = minDistance > tmpDistance ? tmpDistance : minDistance;
            maxDistance  = maxDistance < tmpDistance ? tmpDistance : maxDistance;
        };

        System.out.println("Shortest circuit distance is " + minDistance);
        System.out.println("Longest circuit distance is " + maxDistance);
    }

    public static void swap(String[] input, int a, int b) {
        String tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }


}
