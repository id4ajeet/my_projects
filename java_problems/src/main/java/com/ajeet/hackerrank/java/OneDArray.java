
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class OneDArray {

    public static void main(String[] args) {
        System.out.println(canWin(5, new int[]{0, 0, 0, 1, 1, 1}, 0));
        System.out.println(canWin(26, new int[]{0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0}, 0));
        System.out.println(canWin(3, new int[]{0, 0, 1, 1, 1, 0}, 0));
    }

    // Return true if you can win the game; otherwise, return false.
    public static boolean canWin(int leap, int[] game, int i) {
        int n = game.length;
        if (i < 0 || game[i] == 1) {
            return false;
        }

        if (i == n - 1 || i + leap >= n) {
            return true;
        }

        game[i] = 1; //visited once
        if (canWin(leap, game, i + leap) || canWin(leap, game, i + 1) || canWin(leap, game, i - 1)) {
            return true;
        }

        return false;
    }
}
