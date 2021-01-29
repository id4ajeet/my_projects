
package com.ajeet.hackerrank.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ListExample {

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int d = sc.nextInt();
            data.add(d);
        }

        t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            String x = sc.next();
            int index = sc.nextInt();
            if  (x.equals("Insert")) {
                int value = sc.nextInt();
                data.add(index, value);
            } else {
                data.remove(index);
            }
        }

        String collect = data.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(collect);
    }
}
