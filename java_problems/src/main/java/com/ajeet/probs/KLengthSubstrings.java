package com.ajeet.probs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Two Pointer and Sliding Window Algorithm to Find K-Length Substrings With No Repeated Characters
 * <pre>
 * Given a string S, return the number of substrings of length K with no repeated characters.
 *
 * Example 1:
 * Input: S = “havefunonleetcode”, K = 5
 * Output: 6
 * Explanation:
 * There are 6 substrings they are : ‘havef’,’avefu’,’vefun’,’efuno’,’etcod’,’tcode’.
 *
 * Example 2:
 * Input: S = “home”, K = 5
 * Output: 0
 * Explanation:
 * Notice K can be larger than the length of S. In this case is not possible to find any substring.
 *
 * Note:
 * 1 <= S.length <= 10^4
 * All characters of S are lowercase English letters.
 * 1 <= K <= 10^4
 *  </pre>
 *
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class KLengthSubstrings {

    public static void main(String[] args) {
        System.out.println(findUniqueWords("havefunonleetcode", 5));
        System.out.println(findUniqueWords("home", 5));
        System.out.println(findUniqueWords("adsfjkgasghzbvhjau", 5));
    }

    private static Number findUniqueWords(String input, int k) {

        if (k > input.length()) {
            return 0;
        }

        List<String> substrings = new ArrayList<>();
        int[] flag = new int[26];

        //Initialize flag with 0
        IntStream.range(0, 26).forEach(i -> flag[i] = 0);

        char[] chars = input.toCharArray();

        //Initialize first k chars
        IntStream.range(0, k).forEach(i -> flag[chars[i] - 97]++);

        //Check if first k letters are unique
        AtomicInteger count = new AtomicInteger();
        if (!IntStream.range(0, 26).anyMatch(j -> flag[j] > 1)) {
            count.getAndIncrement();

            substrings.add(new String(chars, 0, k));
        }

        IntStream.range(k, input.length()).forEach(i -> {
            flag[chars[i - k] - 97]--; //i-k reset the value by 1 as it used already, keep available for next
            flag[chars[i] - 97]++; //add counter for current

            //check if i-k to i chars are unique
            if (!IntStream.range(0, 26).anyMatch(j -> flag[j] > 1)) {
                count.getAndIncrement();

                substrings.add(new String(chars, i - k + 1, k));
            }
        });

        System.out.println("Input : " + input + " " + substrings);
        return count;
    }
}
