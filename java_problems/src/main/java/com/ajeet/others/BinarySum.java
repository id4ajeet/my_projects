package com.ajeet.others;

import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BinarySum {


    /**
     * <pre>
     * Given two binary strings (strings containing only 1s and 0s) return their sum (also as a binary string).
     * Note: neither binary string will contain leading 0s unless the string itself is 0
     * Ex: Given the following binary strings...
     * "100" + "1", return "101"
     * "11" + "1", return "100"
     * "1" + "0", return  "1
     * </pre>
     *
     * @param num1 number1
     * @param num2 number2
     * @return sum of numbers
     */
    public String sum1(String num1, String num2) {

        int n1 = Integer.parseInt(num1, 2);
        int n2 = Integer.parseInt(num2, 2);
        return Integer.toBinaryString(n1 + n2);
    }

    public String sum2(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;

        int carry = 0;
        while (index1 >= 0 && index2 >= 0) {

            int d1 = num1.charAt(index1) - '0';
            int d2 = num2.charAt(index2) - '0';

            int digit = (d1 ^ d2) ^ carry;
            carry = (d1 & d2) | (d1 & carry) | (d2 & carry);

            sb.append(digit);

            index1--;
            index2--;
        }

        //if length is not same
        while (index1 >= 0) {
            int d1 = num1.charAt(index1) - '0';
            int digit = (d1 ^ carry);
            carry = d1 & carry;
            sb.append(digit);
            index1--;
        }

        while (index2 >= 0) {
            int d2 = num2.charAt(index2) - '0';
            int digit = (d2 ^ carry);
            carry = d2 & carry;
            sb.append(digit);
            index2--;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    /**
     * Adds padding with 0 to have equal length then sum
     *
     * @param num1 Number1
     * @param num2 Number2
     * @return sum of numbers
     */
    public String sumPad(String num1, String num2) {

        if (num1.length() < num2.length()) {
            int diff = num2.length() - num1.length();
            String collect = IntStream.range(0, diff).mapToObj(o -> "0").collect(Collectors.joining());
            num1 = collect + num1;
        } else {
            int diff = num1.length() - num2.length();
            String collect = IntStream.range(0, diff).mapToObj(o -> "0").collect(Collectors.joining());
            num2 = collect + num2;
        }

        StringBuilder sb = new StringBuilder();

        int index = num1.length() - 1;
        int carry = 0, d1, d2, digit;

        while (index >= 0) {

            d1 = num1.charAt(index) - '0';
            d2 = num2.charAt(index) - '0';

            digit = (d1 ^ d2) ^ carry;
            carry = (d1 & d2) | (d1 & carry) | (d2 & carry);

            sb.append(digit);
            index--;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        BinarySum b = new BinarySum();

        BiConsumer<String, String> sum = (s1, s2) -> {
            System.out.println(b.sum1(s1, s2));
            System.out.println(b.sum2(s1, s2));
            System.out.println(b.sumPad(s1, s2));
        };

        sum.accept("11101", "0101");
        sum.accept("11111", "11111000");
        sum.accept("1", "11111000");

        b.printBit();
        b.printBitCarry();

    }

    public void printBit() {
        System.out.println(String.format("%3s | %3s | %3s | %3s| %3s", "A", "B", "A&B", "A^B", "A+B"));

        BiConsumer<Integer, Integer> method = (a, b) -> System.out.println(String.format("%3d | %3d | %3d | %3d| %3s", a, b, (a & b), (a ^ b), Integer.toBinaryString(a + b)));

        method.accept(0, 0);
        method.accept(0, 1);
        method.accept(1, 0);
        method.accept(1, 1);
    }


    public void printBitCarry() {
        System.out.println(String.format("%5s | %5s | %5s | %5s | %5s | %5s", "C", "A", "B", "Carry", "A^B^C", "A+B+C"));

        TriConsumer<Integer, Integer, Integer> method = (c, a, b) -> System.out.println(String.format("%5d | %5d | %5d | %5d | %5d | %5s", c, a, b, (a & b) | (a & c) | (b & c), (a ^ b) ^ c, Integer.toBinaryString(a + b + c)));

        method.accept(0, 0, 0);
        method.accept(0, 0, 1);
        method.accept(0, 1, 0);
        method.accept(0, 1, 1);
        method.accept(1, 0, 0);
        method.accept(1, 0, 1);
        method.accept(1, 1, 0);
        method.accept(1, 1, 1);
    }

    @FunctionalInterface
    public interface TriConsumer<T, U, V> {
        void accept(T t, U u, V v);
    }

}
