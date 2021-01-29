
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class SplitExample {
    public static void main(String[] args) {
        String s = "He is a very very good boy, isn't he?";
        String[] splits = s.split(" |!|,|\\?|\\.|_|'|@");
        int i = 0;
        int count = 0;
        while (i < splits.length) {
            if (!splits[i].isEmpty()) {
                count++;
            }
            i++;
        }
        System.out.println(count);
        i = 0;
        while (i < splits.length) {
            if (!splits[i].isEmpty()) {
                System.out.println(splits[i]);
            }
            i++;
        }
    }
}
