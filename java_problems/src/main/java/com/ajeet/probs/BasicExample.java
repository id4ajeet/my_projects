
package com.ajeet.probs;

import java.util.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BasicExample {

    public static void main(String[] args) {

        List<List<Long>> sides = new ArrayList<>();
        sides.add(Arrays.asList(2l, 1l));
        sides.add(Arrays.asList(10l, 7l));
        sides.add(Arrays.asList(9l, 5l));
        sides.add(Arrays.asList(6l, 9l));
        sides.add(Arrays.asList(7l, 3l));

        System.out.println(nearlySimilarRectangles(sides));

        System.out.println(decryptPassword("51Pa*0Lp*0e"));
    }

    public static long nearlySimilarRectangles(List<List<Long>> sides) {
        // Write your code here
        int sum = 0;
        Map<Double, Integer> values = new HashMap<>();
        for (int i = 0; i < sides.size(); i++) {
            double value = ((double) sides.get(i).get(0) / (double) sides.get(i).get(1));
            Integer c = values.get(value);
            if (c == null) {
                c = 0;
            } else {
                c++;
                sum += c;
            }
            values.put(value, c);
        }
        return sum;
    }

    /*
     * Complete the 'decryptPassword' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String decryptPassword(String s) {
        // Write your code here
        char[] result = s.toCharArray();
        int start = 0;
        int i = s.length() - 1;

        while (i >= start) {
            char c = s.charAt(i);
            if (c == '0') {
                result[i] = result[start];
                result[start] = ' ';
                start++;
                i--;
            } else if (c == '*') {
                result[i] = result[i - 2];
                result[i - 2] = ' ';
                i -= 2;
            } else {
                i--;
            }
        }

        String ans = "";
        for (i = 0; i < result.length; i++) {
            if (result[i] != ' ') {
                ans += result[i];
            }
        }
        return ans;
    }
}
