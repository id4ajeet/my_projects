
package com.ajeet.probs;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BeautifulString {
    public static void main(String[] args) {
        System.out.println(beautifulStrings("abba"));
    }

    /**
     * You are given a string, , consisting of lowercase English letters.
     *
     * A string is beautiful with respect to  if it can be derived from  by removing exactly 2 characters.
     *
     * Find and print the number of different strings that are beautiful with respect to .
     */
    static long beautifulStrings(String s) {
        Set<String> unique = new HashSet<>();

        for (int i = 0; i < s.length() - 1; i++) {

            String pre = s.substring(0, i);
            String post = s.substring(i + 1);

            for (int j = 0; j < post.length(); j++) {
                String post1 = post.substring(0, j);
                String post2 = post.substring(j + 1);

                unique.add(pre + post1 + post2);
            }
        }
        return unique.size();
    }
}
