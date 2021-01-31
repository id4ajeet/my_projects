package com.ajeet.hackerrank.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class DequeueExample {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        Set<Integer> freq = new HashSet<>();
        int[] arr = new int[]{5, 3, 5, 2, 3, 2};
        int n = arr.length;
        int m = 3;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            deque.add(num);
            freq.add(num);
            if (deque.size() == m) {
                if (max < freq.size()) {
                    max = freq.size();
                }

                Integer pop = deque.pop();
                if (!deque.contains(pop)) {
                    freq.remove(pop);
                }
            }
        }

        System.out.println(max);
    }
}
