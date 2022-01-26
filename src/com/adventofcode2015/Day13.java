package com.adventofcode2015;

import org.jgrapht.Graph;
import org.jgrapht.alg.HamiltonianCycle;
import org.jgrapht.graph.*;

import java.util.List;

public class Day13 {

    static final String input_test = """
            Alice would gain 54 happiness units by sitting next to Bob.
            Alice would lose 79 happiness units by sitting next to Carol.
            Alice would lose 2 happiness units by sitting next to David.
            Bob would gain 83 happiness units by sitting next to Alice.
            Bob would lose 7 happiness units by sitting next to Carol.
            Bob would lose 63 happiness units by sitting next to David.
            Carol would lose 62 happiness units by sitting next to Alice.
            Carol would gain 60 happiness units by sitting next to Bob.
            Carol would gain 55 happiness units by sitting next to David.
            David would gain 46 happiness units by sitting next to Alice.
            David would lose 7 happiness units by sitting next to Bob.
            David would gain 41 happiness units by sitting next to Carol.""";

    static final String input = """
            Alice would lose 2 happiness units by sitting next to Bob.
            Alice would lose 62 happiness units by sitting next to Carol.
            Alice would gain 65 happiness units by sitting next to David.
            Alice would gain 21 happiness units by sitting next to Eric.
            Alice would lose 81 happiness units by sitting next to Frank.
            Alice would lose 4 happiness units by sitting next to George.
            Alice would lose 80 happiness units by sitting next to Mallory.
            Bob would gain 93 happiness units by sitting next to Alice.
            Bob would gain 19 happiness units by sitting next to Carol.
            Bob would gain 5 happiness units by sitting next to David.
            Bob would gain 49 happiness units by sitting next to Eric.
            Bob would gain 68 happiness units by sitting next to Frank.
            Bob would gain 23 happiness units by sitting next to George.
            Bob would gain 29 happiness units by sitting next to Mallory.
            Carol would lose 54 happiness units by sitting next to Alice.
            Carol would lose 70 happiness units by sitting next to Bob.
            Carol would lose 37 happiness units by sitting next to David.
            Carol would lose 46 happiness units by sitting next to Eric.
            Carol would gain 33 happiness units by sitting next to Frank.
            Carol would lose 35 happiness units by sitting next to George.
            Carol would gain 10 happiness units by sitting next to Mallory.
            David would gain 43 happiness units by sitting next to Alice.
            David would lose 96 happiness units by sitting next to Bob.
            David would lose 53 happiness units by sitting next to Carol.
            David would lose 30 happiness units by sitting next to Eric.
            David would lose 12 happiness units by sitting next to Frank.
            David would gain 75 happiness units by sitting next to George.
            David would lose 20 happiness units by sitting next to Mallory.
            Eric would gain 8 happiness units by sitting next to Alice.
            Eric would lose 89 happiness units by sitting next to Bob.
            Eric would lose 69 happiness units by sitting next to Carol.
            Eric would lose 34 happiness units by sitting next to David.
            Eric would gain 95 happiness units by sitting next to Frank.
            Eric would gain 34 happiness units by sitting next to George.
            Eric would lose 99 happiness units by sitting next to Mallory.
            Frank would lose 97 happiness units by sitting next to Alice.
            Frank would gain 6 happiness units by sitting next to Bob.
            Frank would lose 9 happiness units by sitting next to Carol.
            Frank would gain 56 happiness units by sitting next to David.
            Frank would lose 17 happiness units by sitting next to Eric.
            Frank would gain 18 happiness units by sitting next to George.
            Frank would lose 56 happiness units by sitting next to Mallory.
            George would gain 45 happiness units by sitting next to Alice.
            George would gain 76 happiness units by sitting next to Bob.
            George would gain 63 happiness units by sitting next to Carol.
            George would gain 54 happiness units by sitting next to David.
            George would gain 54 happiness units by sitting next to Eric.
            George would gain 30 happiness units by sitting next to Frank.
            George would gain 7 happiness units by sitting next to Mallory.
            Mallory would gain 31 happiness units by sitting next to Alice.
            Mallory would lose 32 happiness units by sitting next to Bob.
            Mallory would gain 95 happiness units by sitting next to Carol.
            Mallory would gain 91 happiness units by sitting next to David.
            Mallory would lose 66 happiness units by sitting next to Eric.
            Mallory would lose 75 happiness units by sitting next to Frank.
            Mallory would lose 99 happiness units by sitting next to George.""";

    public static void main(String[] args) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        for(String line : input.split("\n")){
            String words[] = line.split(" ");
            String from = words[0];
            String to = words[10].replace(".", "");
            Integer value = "gain".equals(words[2]) ? - Integer.parseInt(words[3]) : Integer.parseInt(words[3]);

            if(!graph.containsVertex(from)){
                graph.addVertex(from);
            }
            if(!graph.containsVertex(to)){
                graph.addVertex(to);
            }

            DefaultWeightedEdge edge = graph.getEdge(from, to);

            if(edge != null){
                graph.setEdgeWeight(edge, graph.getEdgeWeight(edge) + value);
            } else {
                edge = graph.addEdge(from, to);
                graph.setEdgeWeight(edge, value);
            }
        }
        List<String> verticeList = HamiltonianCycle
                    .getApproximateOptimalForCompleteGraph(graph);
        verticeList.forEach(System.out::println);
        double sum = 0;
        for (int i = 1; i < verticeList.size(); i++) {
            sum += graph.getEdgeWeight(graph.getEdge(verticeList.get(i - 1), verticeList.get(i)));
        }
        sum += graph.getEdgeWeight(graph.getEdge(verticeList.get(verticeList.size() - 1),
                verticeList.get(0)));
        for (DefaultWeightedEdge e : graph.edgeSet()) {
            System.out.println(e);
            System.out.println(graph.getEdgeWeight(e));
        }
        System.out.println("The total change of happiness is " + Math.abs(sum));
    }
}
