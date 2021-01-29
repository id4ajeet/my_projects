
package com.ajeet.probs;

import java.util.Stack;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class Parser {

    boolean isBalanced(String x) {

        if (x.length() == 0) {
            return true;
        } else if (x.length() % 2 == 1) {
            return false;
        }

        Stack<Character> s = new Stack<>();
        for (char c : x.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                s.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if(s.empty()) {
                    return false;
                }

                char d = s.peek();
                s.pop();

                if (c == ')' && d != '(') {
                    return false;
                } else if (c == '}' && d != '{') {
                    return false;
                } else if (c == ']' && d != '[') {
                    return false;
                }
            }
        }
        return s.empty();
    }

    public static void main(String[] args) {
        System.out.println(new Parser().isBalanced("{{{}(())[()]}}"));
    }
}
