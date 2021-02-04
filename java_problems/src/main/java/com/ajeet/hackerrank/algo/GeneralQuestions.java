package com.ajeet.hackerrank.algo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class GeneralQuestions {
    public static void main(String[] args) {

        List<Integer> res = gradingStudents(Arrays.asList(73, 67, 38, 33));
        res.stream().forEach(System.out::println);

    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        return grades.stream()
            .map(g -> {
                if (g >= 38) {
                    int m = 40;
                    while (g > m) {
                        m = m + 5;
                    }
                    if (m - g < 3) {
                        return m;
                    }
                }
                return g;
            }).collect(Collectors.toList());
    }
}
