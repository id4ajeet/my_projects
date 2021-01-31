
package com.ajeet.hackerrank.java;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class InputMisMatch {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(in);
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println((x / y));
        } catch (InputMismatchException e) {
            System.out.println("java.util.InputMismatchException");
        } catch(ArithmeticException e) {
            System.out.println(e);
        }
    }
}
