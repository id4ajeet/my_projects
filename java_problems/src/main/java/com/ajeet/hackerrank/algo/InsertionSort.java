
package com.ajeet.hackerrank.algo;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class InsertionSort {

    public static void main(String[] args) {
        insertionSort1(5, new int[]{2, 4, 6, 8, 3});
        insertionSort2(6, new int[]{1, 4, 3, 5, 6, 2});
    }


    /*place rightmost element in correct place*/
    static void insertionSort1(int n, int[] arr) {

        Consumer<int[]> print = v -> System.out.println(Arrays.stream(v)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(" ")));

        int key = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
            print.accept(arr);
        }

        arr[j + 1] = key;
        print.accept(arr);
    }

    // Complete the insertionSort2 function below.
    static void insertionSort2(int n, int[] arr) {
        Consumer<int[]> print = v -> System.out.println(Arrays.stream(v)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(" ")));

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
            print.accept(arr);
        }
    }


    /*Find Number of swaps in Insertion sort*/
    static int runningTime(int[] arr) {
        int count = 0;
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
                count++;
            }
            arr[j + 1] = key;
        }

        return count;
    }
}
