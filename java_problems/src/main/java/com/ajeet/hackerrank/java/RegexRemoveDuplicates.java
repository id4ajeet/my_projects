
package com.ajeet.hackerrank.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class RegexRemoveDuplicates {
    public static void main(String[] args) {
        String regex = "\\b(\\w+)(\\s+\\1\\b)*";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        String input = "I love Love to To tO code";

        Matcher m = p.matcher(input);

        // Check for subsequences of input that match the compiled pattern
        while (m.find()) {
            input = input.replaceAll(m.group(0), m.group(1));
        }

        // Prints the modified sentence.
        System.out.println(input);
    }

}
