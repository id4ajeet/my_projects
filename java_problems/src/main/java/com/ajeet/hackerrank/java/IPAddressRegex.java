
package com.ajeet.hackerrank.java;

import java.util.function.Predicate;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class IPAddressRegex {
    public static void main(String[] args) {
        Predicate<String> ipPredicate = v -> v.matches(new MyRegex().pattern);
        System.out.println(ipPredicate.test("000.12.12.034"));
        System.out.println(ipPredicate.test("256.12.12.034"));
        System.out.println(ipPredicate.test("000.A.12.034"));
        System.out.println(ipPredicate.test(".213.123.23.32"));
        System.out.println(ipPredicate.test("23.45.22.32."));
        System.out.println(ipPredicate.test("23.45.-22.32"));
        System.out.println(ipPredicate.test("255.255.255.255"));

        System.out.println(ipPredicate.test("005.255.255.255"));

        for (int i = 0; i < 256; i++) {
            String ip = i + "." + i + "." + i + "." + i;
            if (!ipPredicate.test(ip)) {
                System.out.println(ip + " false");
            }
        }

    }
}

class MyRegex {
    String digits = "([0-9]|[0-9][0-9]|[0,1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5])";
    String dots = "\\.";
    String pattern = digits + dots + digits + dots + digits + dots + digits;
}
