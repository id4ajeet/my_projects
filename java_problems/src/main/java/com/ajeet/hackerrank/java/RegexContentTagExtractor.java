package com.ajeet.hackerrank.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class RegexContentTagExtractor {

    public static void main(String[] args) {
        String[] inputs = new String[]{
            "<h1>Nayeem loves counseling</h1>",
            "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
            "<Amee>safat codes like a ninja</amee>",
            "<SA premium>Imtiaz has a secret crush</SA premium>"
        };

        String regex = "<(.+)>([^<>]+)</(\\1)>";
        Pattern p = Pattern.compile(regex);

        for (String line : inputs) {
            Matcher matcher = p.matcher(line);
            boolean found = false;
            while (matcher.find()) {
                System.out.println(matcher.group(2));
                found = true;
            }
            if (!found) {
                System.out.println("None");
            }
        }
    }
}
