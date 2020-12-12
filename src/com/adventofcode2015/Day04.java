package com.adventofcode2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.
 *
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
 *
 * For example:
 *
 * If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
 * If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 * Your puzzle answer was 117946.
 *
 * --- Part Two ---
 * Now find one that starts with six zeroes.
 *
 * Your puzzle answer was 3938038.
 */
public class Day04 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "ckczppom";
        MessageDigest md = MessageDigest.getInstance("MD5");
        long i = 0;
        Long fiveZeroesFoundIndex = null;
        Long sixZeroesFoundIndex = null;
        while(true){
            md.update((input + i).getBytes());
            byte[] digest = md.digest();
            StringBuilder result = new StringBuilder();
            for (byte aByte : digest) {
                result.append(String.format("%02x", aByte));
            }
            if(fiveZeroesFoundIndex == null && result.toString().startsWith("00000")){
                fiveZeroesFoundIndex = i;
                System.out.println("The smallest number to add to our secret in order to have a hash with 5 leading zeroes is " + fiveZeroesFoundIndex);

            }
            if(sixZeroesFoundIndex == null && result.toString().startsWith("000000")){
                sixZeroesFoundIndex = i;
                System.out.println("The smallest number to add to our secret in order to have a hash with 6 leading zeroes is " + sixZeroesFoundIndex);

            }
            if(fiveZeroesFoundIndex != null && sixZeroesFoundIndex != null){
                break;
            }
            i++;
        }
    }
}
