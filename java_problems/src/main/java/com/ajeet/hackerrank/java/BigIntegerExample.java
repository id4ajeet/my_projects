/*
 * (c) Copyright 2020 Brite:Bill Ltd.
 *
 * 7 Grand Canal Street Lower, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.hackerrank.java;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
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
