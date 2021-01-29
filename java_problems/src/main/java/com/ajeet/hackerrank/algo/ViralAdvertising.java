
package com.ajeet.hackerrank.algo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ViralAdvertising {

    public static void main(String[] args) {
        System.out.println(viralAdvertising(3)); //9
    }

    /**
     * Example : n=5
     * <pre>
     * Day Shared Liked Cumulative
     * 1      5     2       2
     * 2      6     3       5
     * 3      9     4       9
     * 4     12     6      15
     * 5     18     9      24
     * </pre>
     *
     * @param n days
     * @return Cumulative at n
     */
    static int viralAdvertising(int n) {

        int shared = 5;
        int cumulative = 2;
        for (int i = 1; i < n; i++) {

            shared = (shared / 2) * 3;
            cumulative = cumulative + shared / 2;
        }
        return cumulative;
    }
}
