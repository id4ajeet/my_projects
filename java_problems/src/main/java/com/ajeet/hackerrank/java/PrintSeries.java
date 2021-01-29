
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class PrintSeries {

    public static void main(String[] args) {
        printSeries(0, 2, 10);
    }

    /**
     * <pre>
     *  (a+2^0*b), (a+2^0*b+2^1*b), .... (a+2^0*b+2^1*b ...2^n*b)
     * </pre>
     *
     * @param a 0
     * @param b 2
     * @param n 10
     *
     *          output : 2 6 14 30 62 126 254 510 1022 2046
     */
    static void printSeries(int a, int b, int n) {
        int twoPower = 1;
        int prev = a + b;
        String series = String.valueOf(prev);

        for (int i = 1; i < n; i++) {
            twoPower = twoPower * 2;
            prev = prev + twoPower * b;
            series = series + " " + prev;
        }
        System.out.println(series);
    }
}
