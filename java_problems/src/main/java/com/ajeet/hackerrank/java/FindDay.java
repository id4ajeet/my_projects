
package com.ajeet.hackerrank.java;

import java.util.Calendar;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class FindDay {
    public static void main(String[] args) {
        System.out.println(findDay(8, 5, 2015));
    }

    public static String findDay(int month, int day, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month -1 ,day);

        int d = calendar.get(Calendar.DAY_OF_WEEK);

        switch (d) {
            case 1:
                return "SUNDAY";
            case 2:
                return "MONDAY";
            case 3:
                return "TUESDAY";
            case 4:
                return "WEDNESDAY";
            case 5:
                return "THURSDAY";
            case 6:
                return "FRIDAY";
            case 7:
                return "SATURDAY";
        }
        return null;
    }
}
