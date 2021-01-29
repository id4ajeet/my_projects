
package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Generics {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.printArray(new Integer[]{2, 4, 6});
        printer.printArray(new String[]{"ABC", "XYZ"});
    }
}

class Printer {
    <T> void printArray(T[] array) {
        for (T val : array) {
            System.out.println(val);
        }
    }
}
