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
public class LambdaExample {
    public static void main(String[] args) {

        MyMath ob = new MyMath();
        PerformOperation op;
        boolean ret = false;
        String ans = null;

        int num = 100;
        op = ob.isOdd();
        ret = ob.checker(op, num);
        ans = (ret) ? "ODD" : "EVEN";
        System.out.println(ans);

        op = ob.isPrime();
        ret = ob.checker(op, num);
        ans = (ret) ? "PRIME" : "COMPOSITE";
        System.out.println(ans);

        op = ob.isPalindrome();
        ret = ob.checker(op, num);
        ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
        System.out.println(ans);
    }
}

class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd() {
        return n -> n % 2 != 0;
    }

    public PerformOperation isPrime() {
        return n -> {
            boolean p = true;
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    p = false;
                    break;
                }
            }
            return p && n > 1;
        };
    }

    public PerformOperation isPalindrome() {
        return n -> {
            int rev = 0;
            int actual = n;
            while (n > 0) {
                rev = rev * 10 + n % 10;
                n = n / 10;
            }
            return actual == rev;
        };
    }
}

interface PerformOperation {
    boolean check(int num);
}
