
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class IsAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("Hello", "hello"));
    }

    static boolean isAnagram(String a, String b) {
        // Complete the function
        int[] freq = new int[26];
        for (char c : a.toLowerCase().toCharArray()) {
            int index = c - 'a';
            freq[index] = freq[index] + 1;
        }

        for (char c : b.toLowerCase().toCharArray()) {
            int index = c - 'a';
            freq[index] = freq[index] - 1;
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
