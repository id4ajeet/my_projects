
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class DivisorSum {
    public static void main(String[] args) {

    }

    static int divisor_sum(int n) {
        int sum = 1;
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
