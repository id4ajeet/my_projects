
package com.ajeet.hackerrank.algo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class MissingNumbers {

    public static void main(String[] args) {

        int[] numbers = missingNumbers(new int[]{203, 204, 205, 206, 207, 208, 203, 204, 205, 206},
            new int[]{203, 204, 204, 205, 206, 207, 205, 208, 203, 206, 205, 206, 204});

        System.out.println(Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(" ")));
    }

    static int[] missingNumbers(int[] arr, int[] brr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < brr.length; i++) {
            Integer count = freq.get(brr[i]);
            freq.put(brr[i], count == null ? 1 : count + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            Integer count = freq.get(arr[i]);
            freq.put(arr[i], count == null ? 0 : count - 1);
        }

        return freq.entrySet().stream()
            .filter(e->e.getValue() > 0)
            .map(Map.Entry::getKey)
            .mapToInt(Integer::intValue)
            .sorted()
            .toArray();
    }
}
