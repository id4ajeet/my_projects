
package com.ajeet.hackerrank.algo;

import java.util.function.BiFunction;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CountApplesAndOranges {

    public static void main(String[] args) {
        countApplesAndOranges(7, 11, 5, 15, new int[]{-2, 2, 1,}, new int[]{5, -6});
    }

    /**
     * countApplesAndOranges has the following parameter(s):
     *
     * @param s:       integer, starting point of Sam's house location.
     * @param t:       integer, ending location of Sam's house location.
     * @param a:       integer, location of the Apple tree.
     * @param b:       integer, location of the Orange tree.
     * @param apples:  integer array, distances at which each apple falls from the tree.
     * @param oranges: integer array, distances at which each orange falls from the tree
     *
     *                 Output Format
     *
     *                 Print two integers on two different lines:
     *
     *                 The first integer: the number of apples that fall on Sam's house. The second integer: the number
     *                 of oranges that fall on Sam's house.
     */
    static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {

        BiFunction<int[], Integer, Integer> find = (fruits, tree) -> {
            int count = 0;
            for (int i = 0; i < fruits.length; i++) {
                int position = tree + fruits[i];
                if (position >= s && position <= t) {
                    count++;
                }
            }
            return count;
        };

        System.out.println(find.apply(apples, a));
        System.out.println(find.apply(oranges, b));
    }
}
