
package com.ajeet.hackerrank.java;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BigIntegerExample {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger a = new BigInteger(sc.next());
        BigInteger b = new BigInteger(sc.next());

        System.out.println(a.add(b));
        System.out.println(a.multiply(b));

        System.out.println(a.isProbablePrime(1));
    }
}
