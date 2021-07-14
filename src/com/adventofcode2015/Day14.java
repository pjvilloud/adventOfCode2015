package com.adventofcode2015;

public class Day14 {
    static String input = """
            Vixen can fly 8 km/s for 8 seconds, but then must rest for 53 seconds.
            Blitzen can fly 13 km/s for 4 seconds, but then must rest for 49 seconds.
            Rudolph can fly 20 km/s for 7 seconds, but then must rest for 132 seconds.
            Cupid can fly 12 km/s for 4 seconds, but then must rest for 43 seconds.
            Donner can fly 9 km/s for 5 seconds, but then must rest for 38 seconds.
            Dasher can fly 10 km/s for 4 seconds, but then must rest for 37 seconds.
            Comet can fly 3 km/s for 37 seconds, but then must rest for 76 seconds.
            Prancer can fly 9 km/s for 12 seconds, but then must rest for 97 seconds.
            Dancer can fly 37 km/s for 1 seconds, but then must rest for 36 seconds.""";

    static String input_test = """
            Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
            Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.""";

    static Integer duration = 2503;
    static Integer duration_test = 1000;

    public static void main(String[] args) {

        double maxDistance = 0;
        String winner = null;

        for(String line : input.split("\n")){
            String[] words = line.split(" ");
            String name = words[0];
            double speed = Double.parseDouble(words[3]);
            double sprintDuration = Double.parseDouble(words[6]);
            double restDuration = Double.parseDouble(words[13]);

            double nbOfCycles = duration.doubleValue() / (sprintDuration + restDuration);
            double nbOfSpeedCycles = Math.floor(nbOfCycles);
            if(nbOfCycles - nbOfSpeedCycles >= sprintDuration / (sprintDuration + restDuration)){
                nbOfSpeedCycles++;
            }
            double totalDistance = nbOfSpeedCycles * sprintDuration * speed;
            if(maxDistance < totalDistance){
                winner = name;
                maxDistance = totalDistance;
            }
        }
        System.out.println("The winner is " + winner + ", he traveled " + maxDistance + " km");

    }


}

