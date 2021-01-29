package com.ajeet.probs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class BookingAssignment {
    public static void main(String[] args) {
        System.out.println(perfectSubstring("1020122", 2));


    }

    public static int countDuplicate(List<Integer> numbers) {
        // Write your code here
        Map<Integer, Integer> store = new HashMap<>();
        numbers.forEach(num -> {
            Integer count = store.get(num);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            store.put(num, count);
        });

        return (int) store.values().stream().filter(v -> v > 1).count();
    }


    public static int perfectSubstring(String s, int k) {
        // Write your code here
        int pSqr = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] store = new int[10];
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - '0';
                store[index]++;
                if (store[index] > k) {
                    break;
                }

                if (!Arrays.stream(store).anyMatch(v -> v > 0 && v != k)) {
                    pSqr++;
                }

            }
        }
        return pSqr;
    }


    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {
        // Write your code here
        Map<Integer, Integer> hotelReviews = new HashMap<>();
        hotelIds.forEach(id -> hotelReviews.put(id, 0));

        positiveKeywords = " " + positiveKeywords + " ";
        negativeKeywords = " " + negativeKeywords + " ";


        for (int i = 0; i < reviews.size(); i++) {
            int id = hotelIds.get(i);
            int score = hotelReviews.get(id);
            String review = reviews.get(i);
            for (String keyword : review.replace("\\.", "").split(" ")) {
                String key = " " + keyword + " ";
                if (negativeKeywords.contains(key)) {
                    score -= 1;
                } else if (positiveKeywords.contains(key)) {
                    score += 3;
                }
            }
            hotelReviews.put(id, score);
        }

        List<Integer> selected = hotelReviews.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        if(hotelReviews.size() <= k) {
            return selected;
        }

        return selected.subList(0, k);
    }
}
