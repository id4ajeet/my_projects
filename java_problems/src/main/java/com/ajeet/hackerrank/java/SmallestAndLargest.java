
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SmallestAndLargest {

    public static void main(String[] args) {
        System.out.println(getSmallestAndLargest("welcometojava", 3));
    }

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);

        for (int i = 1; i < s.length() - k + 1; i++) {
            String sub = s.substring(i, i + k);
            if (smallest.compareTo(sub) > 0) {
                smallest = sub;
            }
            if (largest.compareTo(sub) < 0) {
                largest = sub;
            }
        }

        return smallest + "\n" + largest;
    }
}
