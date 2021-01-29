
package com.ajeet.hackerrank.algo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BeautifulDays {

    public static void main(String[] args) {
        System.out.println(beautifulDays(20, 23, 6));  //2
    }

    /***
     * beautifulDays has the following parameter(s):
     *
     * @param i: the starting day number
     * @param j: the ending day number
     * @param k: the divisor
     * @return the number of beautiful days in the range
     */
    static int beautifulDays(int i, int j, int k) {

        int count = 0;
        for (int num = i; num <= j; num++) {
            int reverse = 0;
            int temp = num;
            while (temp > 0) {
                reverse = reverse * 10 + temp % 10;
                temp = temp / 10;
            }

            if ((num - reverse) % k == 0) {
                count++;
            }
        }
        return count;
    }
}
