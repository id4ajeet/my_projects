
package com.ajeet.hackerrank.java;

import java.util.regex.Pattern;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class PatternCompile {
    public static void main(String[] args) {
        String pattern = "[AZ[a-z](a-z)";
        try {
            Pattern.compile(pattern);
            System.out.println("Valid");
        } catch (Exception e) {
            System.out.println("Invalid");
        }
    }
}
