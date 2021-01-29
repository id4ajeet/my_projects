
package com.ajeet.probs;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class FormatIn24Hour {

    public static void main(String[] args) {
        System.out.println(timeConversion("07:05:45PM"));
    }

    static String timeConversion(String s) {
        int hour = Integer.parseInt(s.substring(0, 2));
        if (s.endsWith("PM") && hour < 12) {
            hour = hour + 12;
        } else if (s.endsWith("AM") && hour == 12) {
            hour = 0;
        }
        if (hour < 10) {
            return "0" + hour + s.substring(2, 8);
        } else {
            return hour + s.substring(2, 8);
        }
    }
}
