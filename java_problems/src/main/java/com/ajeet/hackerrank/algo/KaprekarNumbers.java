
package com.ajeet.hackerrank.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class KaprekarNumbers {
    public static void main(String[] args) {
        kaprekarNumbers(1, 100000);
    }

    static void kaprekarNumbers(int p, int q) {

        List<Integer> ans = new ArrayList<>();
        for (int i = p; i <= q; i++) {
            String s = String.valueOf(i);
            String square = String.valueOf((long) i * (long) i);

            String right = square.substring(square.length() - s.length());
            String left = "0" + square.substring(0, square.length() - s.length());

            long sum = Long.parseLong(left) + Long.parseLong(right);

            if (sum == i) {
                ans.add(i);
            }
        }

        if (ans.isEmpty()) {
            System.out.println("INVALID RANGE");
        } else {
            System.out.println(ans.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
