
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class IfElse {
    public static void main(String[] args) {
        int N = 25;

        if (N % 2 == 1) {
            System.out.println("Weird");
        } else {
            if(N >=2 && N<=5) {
                System.out.println("Not Weird");
            } else if(N >=6 && N<=20) {
                System.out.println("Weird");
            } else {
                System.out.println("Not Weird");
            }
        }
    }
}
