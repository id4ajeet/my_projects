
package com.ajeet.probs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Codilty02 {

    public static void main(String[] args) {


        Codilty02 ob = new Codilty02();
        //System.out.println(ob.solution("aaaabbbb"));
        System.out.println(ob.solution("ccaaffddecee"));
        //System.out.println(ob.solution("88"));
    }

    public int solution(String S) {
        // write your code in Java SE 8
        char[] chars = S.toCharArray();

        //Store the frequency
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            Integer count = freq.get(chars[i]);

            if (count == null) {
                freq.put(chars[i], 1);
            } else {
                freq.put(chars[i], count + 1);
            }
        }

        //put count in priority queue
        Queue<Integer> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e2, e1));
        freq.entrySet().forEach(e -> queue.add(e.getValue()));

        int removeCount = 0;

        while (!queue.isEmpty()) {
            int top = queue.remove();
            if (queue.isEmpty()) {
                return removeCount;
            }

            if (queue.peek() == top) {
                if (top > 1) {
                    queue.add(top - 1);
                }
                removeCount++;
            }
        }
        return removeCount;
    }
}
