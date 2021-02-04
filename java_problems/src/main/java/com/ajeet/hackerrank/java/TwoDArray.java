package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class TwoDArray {

    /**
     * <pre>
     * there are many hourglasses in the array above. The three leftmost hourglasses are the following:
     *
     * 1 1 1     1 1 0     1 0 0
     *   1         0         0
     * 1 1 1     1 1 0     1 0 0
     * The sum of an hourglass is the sum of all the numbers within it. The sum for the hourglasses above are 7, 4, and 2, respectively.
     *
     * In this problem you have to print the largest sum among all the hourglasses in the array
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] arr = new int[][]{
            {1, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0},
            {0, 0, 2, 4, 4, 0},
            {0, 0, 0, 2, 0, 0},
            {0, 0, 1, 2, 4, 0}};
        int max = Integer.MIN_VALUE;
        for (int i = 2; i < arr.length; i++) {
            for (int j = 2; j < arr[i].length; j++) {
                int sum = arr[i - 2][j - 2] + arr[i - 2][j - 1] + arr[i - 2][j]
                    + arr[i - 1][j - 1] +
                    arr[i][j - 2] + arr[i][j - 1] + arr[i][j];

                System.out.println("\n" + arr[i - 2][j - 2] + " " + arr[i - 2][j - 1] + " " + arr[i - 2][j] + " \n "
                    + " " + arr[i - 1][j - 1] + "\n" +
                    arr[i][j - 2] + " " + arr[i][j - 1] + " " + arr[i][j]);

                if (max < sum) {
                    max = sum;
                }
            }
        }

        System.out.println(max);
    }

}
