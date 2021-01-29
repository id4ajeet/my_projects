
package com.ajeet.hackerrank.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class MapExample {

    public static void main(String[] argh) {
        Map<String, Integer> phonebook = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            int phone = in.nextInt();
            phonebook.put(name, phone);
            in.nextLine();
        }
        while (in.hasNext()) {
            String s = in.nextLine();
            Integer p = phonebook.get(s);
            System.out.println(p == null ? "Not found" : s + "=" + p);
        }
    }
}
