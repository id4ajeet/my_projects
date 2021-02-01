package com.ajeet.hackerrank.java;

import java.util.BitSet;
import java.util.Scanner;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BitSetExample {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        BitSet[] b = new BitSet[2];
        b[0] = new BitSet(n);
        b[1] = new BitSet(n);

        for (int i = 0; i < m; i++) {
            String op = sc.next();
            int j = sc.nextInt();
            int k = sc.nextInt();

            if ("AND".equals(op)) {
                b[j - 1].and(b[k - 1]);
            } else if ("OR".equals(op)) {
                b[j - 1].or(b[k - 1]);
            } else if ("XOR".equals(op)) {
                b[j - 1].xor(b[k - 1]);
            } else if ("FLIP".equals(op)) {
                b[j - 1].flip(k);
            } else if ("SET".equals(op)) {
                b[j - 1].set(k);
            }

            System.out.println(b[0].cardinality() + " " + b[1].cardinality());
        }
    }
}
