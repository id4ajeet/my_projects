
package com.ajeet.hackerrank.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SockMerchantPairs {

    public static void main(String[] args) {
        System.out.println(sockMerchant(9, new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20}));
    }

    static int sockMerchant(int n, int[] ar) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int s : ar) {
            Integer count = freq.get(s);
            count = (count == null) ? 1 : count + 1;
            freq.put(s, count);
        }
        int count = 0;
        for (int v : freq.values()) {
            count = count + v / 2;
        }
        return count;
    }
}
