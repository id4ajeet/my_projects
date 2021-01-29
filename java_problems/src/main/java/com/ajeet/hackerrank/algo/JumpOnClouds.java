
package com.ajeet.hackerrank.algo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class JumpOnClouds {

    private int twoJump;

    public static void main(String[] args) {
        System.out.println(jumpingOnClouds(new int[]{0, 0, 1, 0, 0, 1, 0}));
        System.out.println(jumpingOnClouds(new int[]{0, 0, 0, 0, 1, 0}));
        System.out.println(jumpingOnClouds(new int[]{0, 0, 0, 1, 0, 0}));

    }

    static int jumpingOnClouds(int[] c) {
        int count = 0;
        int i = 0;
        while (i < c.length - 1) {
            int oneJump = c[i + 1];
            int twoJump = i + 2 < c.length ? c[i + 2] : -1;

            if (twoJump == 0) {
                count++;
                i = i + 2;
            } else if (oneJump == 0) {
                count++;
                i++;
            }
        }
        return count;
    }
}
