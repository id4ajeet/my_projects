
package com.ajeet.probs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class TipiCodility {

    public static void main(String[] args) {
        TipiCodility ob = new TipiCodility();
        System.out.println(ob.solution1("A586QK", "JJ653K"));

        System.out.println(ob.solution2(new int[]{0, 4, -1, 0, 3}, new int[]{0, -2, 5, 0, 3}));
        System.out.println(ob.solution2(new int[]{2, -2, -3, 3}, new int[]{0, 0, 4, -4}));
        System.out.println(ob.solution2(new int[]{4, -1, 0, 3}, new int[]{-2, 6, 0, 4}));
        System.out.println(ob.solution2(new int[]{3, 2, 6}, new int[]{4, 1, 6}));
        System.out.println(ob.solution2(new int[]{1, 4, 2, -2, 5}, new int[]{7, -2, -2, 2, 5}));
    }

    public int solution2(int[] A, int[] B) {
        int count = 0;
        System.out.println(Arrays.toString(A) + " " + Arrays.toString(B));
        for (int k = 1; k < A.length; k++) {
            long suma1 = sum(0, k, A);
            long suma2 = sum(k, A.length, A);
            long sumb1 = sum(0, k, B);
            long sumb2 = sum(k, B.length, B);

            if (suma1 == suma2 && suma2 == sumb1 && sumb1 == sumb2) {
                count++;
            }
        }

        return count;
    }

    public long sum(int start, int end, int[] F) {
        long s = 0l;
        for (int i = start; i < end; i++) {
            s = s + F[i];
        }
        return s;
    }

    public int solution1(String A, String B) {

        Map<Character, Integer> ranks = new HashMap<>();
        IntStream.range(2, 10)
            .forEach(k -> ranks.put((char) (48 + k), k));
        ranks.put('T', 10);
        ranks.put('J', 11);
        ranks.put('Q', 12);
        ranks.put('K', 13);
        ranks.put('A', 14);

        int wins = 0;
        for (int i = 0; i < A.length(); i++) {
            char a = A.charAt(i);
            char b = B.charAt(i);

            if (ranks.get(a) > ranks.get(b)) {
                wins++;
            }
        }
        return wins;
    }

}
