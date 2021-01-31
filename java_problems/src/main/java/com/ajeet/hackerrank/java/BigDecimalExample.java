
package com.ajeet.hackerrank.java;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BigDecimalExample {
    public static void main(String[] args) {
        String[] s = {"9"
            , "-100"
            , "50"
            , "0"
            , "56.6"
            , "90"
            , "0.12"
            , ".12"
            , "02.34"
            , "000.000"
            , null};

        // sort(s);
        //sort1(s);
        sort2(s);
    }

    private static void sort2(String[] s) {
        int n = s.length - 1;
        while (s[n] == null) {
            n--;
        }

        String [] data = new String[n + 1];
        for (int i = 0; i <= n; i++) {
            data[i] = s[i];
        }

        Arrays.sort(data, (o1, o2) -> {
            BigDecimal b1 = new BigDecimal(o1);
            BigDecimal b2 = new BigDecimal(o2);

            return b2.compareTo(b1);
        });

        s = data;

        Arrays.stream(s).forEach(System.out::println);
    }

    private static void sort1(String[] s) {

        int n = s.length - 1;
        while (s[n] == null) {
            n--;
        }

        BigDecimal[] data = new BigDecimal[n + 1];
        for (int i = 0; i <= n; i++) {
            data[i] = new BigDecimal(s[i]);
        }
        Arrays.sort(data, Comparator.reverseOrder());

        for (int i = 0; i < data.length; i++) {
            s[i] = data[i].toString();
        }

        Arrays.stream(s).filter(v -> v != null).forEach(System.out::println);
    }

    private static void sort(String[] s) {
        s = Arrays.stream(s)
            .filter(v -> v != null)
            .map(BigDecimal::new)
            .sorted(Comparator.reverseOrder())
            .map(v -> v.toPlainString())
            .toArray(String[]::new);

        Arrays.stream(s).forEach(System.out::println);
    }
}
