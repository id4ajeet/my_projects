
package com.ajeet.hackerrank.algo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class RepeatedString {

    public static void main(String[] args) {
        System.out.println(repeatedString("aba", 10)); //7
        System.out.println(repeatedString("a", 100000000));
    }

    /**
     * @param s string to repeat
     * @param n the number of characters to consider
     * @return the frequency of a in the substring
     */
    static long repeatedString(String s, long n) {
        long count = 0;
        int len = s.length();
        int[] aTillIndex = new int[len];
        aTillIndex[0] = s.charAt(0) == 'a' ? 1 : 0;

        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == 'a') {
                aTillIndex[i] = aTillIndex[i - 1] + 1;
            } else {
                aTillIndex[i] = aTillIndex[i - 1];
            }
        }

        if (len > n) {
            count = aTillIndex[(int) n];
        } else {
            long multi = n / len;
            long remainder = n % len;

            count = aTillIndex[len - 1] * multi;
            if(remainder > 0){
                count +=aTillIndex[(int) (remainder-1)];
            }
        }
        return count;
    }
}
