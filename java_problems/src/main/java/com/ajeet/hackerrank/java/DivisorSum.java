/*
 * (c) Copyright 2020 Brite:Bill Ltd.
 *
 * 7 Grand Canal Street Lower, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:AjeetKumar.Singh1@britebill.com">Ajeet</a>
 */
public class DivisorSum {
    public static void main(String[] args) {

    }

    static int divisor_sum(int n) {
        int sum = 1;
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
