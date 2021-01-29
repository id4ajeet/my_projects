
package com.ajeet.hackerrank.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CutTheSticks {

    public static void main(String[] args) {
        int[] result = cutTheSticks(new int[]{5, 4, 4, 2, 2, 8});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * <pre>
     * Example
     * arr = [1,2,3] The shortest stick length is 1,
     * so cut that length from the longer two and discard the pieces of length 1.
     * Now the lengths are [1,2]. Again, the shortest stick is of length 1,
     * so cut that amount from the longer stick and discard those pieces.
     * There is only one stick left, arr=[1], so discard that stick.
     * The number of sticks at each iteration are . answer[3,2,1]
     * </pre>
     */
    static int[] cutTheSticks(int[] arr) {
        Arrays.sort(arr);
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < arr.length) {
            ans.add(arr.length - i);
            int temp = arr[i];
            int j = i;
            int count = 0;
            while (j < arr.length) {
                arr[j] = arr[j] - temp;
                if (arr[j] == 0) {
                    count++;
                }
                j++;
            }
            i = i + count;
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
