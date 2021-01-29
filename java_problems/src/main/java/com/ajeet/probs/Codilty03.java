
package com.ajeet.probs;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Codilty03 {

    public static void main(String[] args) {

        Codilty03 ob = new Codilty03();
        System.out.println(ob.solution(new int[]{5, 2, 4, 6, 3, 7}));
    }


    public int solution(int[] A) {
        // write your code in Java SE 8

        int min = Integer.MAX_VALUE;
        for (int p = 1; p < A.length - 1; p++) {
            for (int q = p + 1; q < A.length - 1; q++) {
                if (min > A[p] + A[q]) {
                    min = A[p] + A[q];
                    System.out.println(min + " " + p + " " + q + " " + A[p] + " " + A[q]);
                }
            }
        }
        return min;
    }
}
