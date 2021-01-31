
package com.ajeet.hackerrank.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class PrimeNumbers {

    public static void main(String[] args) {

        int n1 = 2;
        int n2 = 1;
        int n3 = 3;
        int n4 = 4;
        int n5 = 5;

        PrimeNumbers ob = new PrimeNumbers();
        ob.checkPrime(n1);
        ob.checkPrime(n1, n2);
        ob.checkPrime(n1, n2, n3);
        ob.checkPrime(n1, n2, n3, n4, n5);
    }

    private void checkPrime(int... n) {
        List<Integer> primes = new ArrayList<>();
        for (int d : n) {
            boolean p = true;
            for (int i = 2; i <= d / 2; i++) {
                if (d % i == 0) {
                    p = false;
                    break;
                }
            }
            if (p && d > 1) {
                primes.add(d);
            }
        }

        String output = "";
        if (!primes.isEmpty()) {
            output = primes.get(0).toString();
        }

        for (int i = 1; i < primes.size(); i++) {
            output = output + " " + primes.get(i).toString();
        }
        System.out.println(output);
    }

    private void add(int a, int... n) {
        int sum = a;
        String s = String.valueOf(a);
        for (int b : n) {
            s += "+" + b;
            sum += b;
        }
        s += "=" + sum;
        System.out.println(s);
    }

}
