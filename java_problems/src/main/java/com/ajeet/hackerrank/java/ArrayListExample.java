
package com.ajeet.hackerrank.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class ArrayListExample {

    public static void main(String[] args) {

        List<List<Integer>> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int size = sc.nextInt();
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                data.add(sc.nextInt());
            }
            store.add(data);
        }

        t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int row = sc.nextInt() - 1;
            int column = sc.nextInt() - 1;

            try {
                System.out.println(store.get(row).get(column));
            } catch (Exception e) {
                System.out.println("ERROR!");
            }
        }
    }

}
