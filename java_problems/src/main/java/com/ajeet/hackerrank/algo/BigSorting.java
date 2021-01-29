
package com.ajeet.hackerrank.algo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BigSorting {

    public static void main(String[] args) {
        String[] result = bigSorting(new String[]{
            "8",
            "1",
            "2",
            "100",
            "12303479849857341718340192371",
            "3084193741082937",
            "3084193741082938",
            "111",
            "200"
        });

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    static String[] bigSorting(String[] unsorted) {

        int n = unsorted.length;
        for (int i = 1; i < n; ++i) {
            String key = unsorted[i];
            int j = i - 1;

            while (j >= 0 && compare(unsorted[j], key)) {
                unsorted[j + 1] = unsorted[j];
                j = j - 1;
            }
            unsorted[j + 1] = key;
        }

        return unsorted;
    }

    static boolean compare(String v1, String v2) {

        if (v1.length() == v2.length()) {
            return v1.compareTo(v2) > 0;
        }
        return v1.length() > v2.length();
    }
}
