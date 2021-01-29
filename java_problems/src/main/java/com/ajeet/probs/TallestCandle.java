
package com.ajeet.probs;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class TallestCandle {
    public static void main(String[] args) {
        //Ans is 2 as 4 is twice
        System.out.println(birthdayCakeCandles(Arrays.asList(1, 4, 3, 2, 4, 2)));
    }

    public static int birthdayCakeCandles(List<Integer> candles) {
        // Write your code here
        int max = candles.stream().mapToInt(Integer::intValue).max().getAsInt();
        return (int) candles.stream().filter(v -> v == max).count();
    }
}
