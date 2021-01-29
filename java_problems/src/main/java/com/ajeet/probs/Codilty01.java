
package com.ajeet.probs;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Codilty01 {

    public static void main(String[] args) {
        Codilty01 ob = new Codilty01();
        System.out.println(ob.solution("50552"));
        System.out.println(ob.solution("10101"));
        System.out.println(ob.solution("88"));
    }

    public int solution(String S) {
        // write your code in Java SE 8
        char[] chars = S.toCharArray();
        char zero = '0';
        int max = 0;
        for (int i = 0; i < S.length() - 1; i++) {
            int number = (chars[i] - zero) * 10 + (chars[i + 1] - zero);
            if (number > max) {
                max = number;
            }
        }
        return max;
    }
}
