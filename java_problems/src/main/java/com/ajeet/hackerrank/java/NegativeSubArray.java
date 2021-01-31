package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class NegativeSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1, -2, 4, -5, 1};
        int n = arr.length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum = sum + arr[j];
                if (sum < 0) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
