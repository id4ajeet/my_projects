
package com.ajeet.hackerrank.java;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class HashSetExample {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        Set<String> unique = new HashSet<>();
        for (int i = 0; i < t; i++) {
            unique.add(pair_left[i]+"____"+pair_right[i]);
            System.out.println(unique.size());
        }

    }


}
