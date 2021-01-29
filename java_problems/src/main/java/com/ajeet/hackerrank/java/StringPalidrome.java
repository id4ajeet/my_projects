
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class StringPalidrome {
    public static void main(String[] args) {
        String A = "madam";

        System.out.println(new StringBuilder(A).reverse().toString().compareTo(A) == 0 ? "Yes" : "No");
    }

}
