
package com.ajeet.hackerrank.algo;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CountingValleys {
    public static void main(String[] args) {
        System.out.println(countingValleys(8, "UDDDUDUU"));
    }

    /**
     * @param steps the number of steps on the hike
     * @param path  a string describing the path
     * @return the number of valleys traversed
     */
    public static int countingValleys(int steps, String path) {
        // Write your code here
        int valley = 0;
        int current = 0;
        boolean valleyEnter = false;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'D') {
                current = current - 1;
            } else {
                current = current + 1;
            }

            if (current == -1) {
                valleyEnter = true;
            } else if (current == 0) {
                if (valleyEnter) {
                    valley++;
                }
                valleyEnter = false;
            }
        }
        return valley;
    }
}
