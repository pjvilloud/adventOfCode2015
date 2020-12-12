package com.adventofcode2015;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 11: Corporate Policy ---
 * Santa's previous password expired, and he needs help choosing a new one.
 *
 * To help him remember his new password after the old one expires, Santa has devised a method of coming up with a password based on the previous one. Corporate policy dictates that passwords must be exactly eight lowercase letters (for security reasons), so he finds his new password by incrementing his old password string repeatedly until it is valid.
 *
 * Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so on. Increase the rightmost letter one step; if it was z, it wraps around to a, and repeat with the next letter to the left until one doesn't wrap around.
 *
 * Unfortunately for Santa, a new Security-Elf recently started, and he has imposed some additional password requirements:
 *
 * Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
 * Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
 * Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
 * For example:
 *
 * hijklmmn meets the first requirement (because it contains the straight hij) but fails the second requirement requirement (because it contains i and l).
 * abbceffg meets the third requirement (because it repeats bb and ff) but fails the first requirement.
 * abbcegjk fails the third requirement, because it only has one double letter (bb).
 * The next password after abcdefgh is abcdffaa.
 * The next password after ghijklmn is ghjaabcc, because you eventually skip all the passwords that start with ghi..., since i is not allowed.
 * Given Santa's current password (your puzzle input), what should his next password be?
 *
 * Your puzzle answer was hxbxxyzz.
 *
 * --- Part Two ---
 * Santa's password expired again. What's the next one?
 *
 * Your puzzle answer was hxcaabcc.
 *
 * Both parts of this puzzle are complete! They provide two gold stars: **
 *
 * At this point, you should return to your Advent calendar and try another puzzle.
 *
 * Your puzzle input was hxbxwxba.
 */
public class Day11 {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String input = "hxbxxyzz";
        String regex = ".{3}";
        Pattern pattern = Pattern.compile(regex);

        while(true){
            input = incrementString(input);
            if(Pattern.compile(".*[iol]+.*").matcher(input).find()){
                continue;
            }
            if(!Pattern.compile(".*(.)\\1.*([^\\\\1])\\2.*").matcher(input).find()){
                continue;
            }
            Matcher matcher = pattern.matcher(input);
            boolean rule3 = false;
            if(matcher.find()){
                do{
                    if(ALPHABET.contains(matcher.group())){
                        rule3 = true;
                        break;
                    }
                }
                while(matcher.find(matcher.start() + 1));
            }

            if(rule3){
                break;
            }
        }
        System.out.print("Correct password is " + input);

    }

    public static String incrementString(String input){
        StringBuilder output = new StringBuilder(input);
        for (int i = input.length() - 1; i >=0 ; i--) {
            if(input.charAt(i) == 'z'){
                output.setCharAt(i, 'a');
                continue;
            }
            output.setCharAt(i, ALPHABET.charAt(ALPHABET.indexOf(Character.toString(input.charAt(i))) + 1));
            break;
        }
        return output.toString();
    }
}
