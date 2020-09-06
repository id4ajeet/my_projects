package com.ajeet.fxpro;

import java.util.Arrays;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CalculateLandscape {

    public static void main(String[] args) {

        Runnable syntex = () -> {
            System.out.println("Please provide correct landScape data");
            System.out.println("Example:");
            System.out.println("java com.ajeet.CalculateLandscape 5 2 3 4 5 4 0 3 1");
        };

        if (args.length < 1) {
            syntex.run();
            return;
        }

        int input[] = new int[args.length];

        try {
            for (int i = 0; i < args.length; i++) {
                input[i] = Integer.parseInt(args[i]);
                if (input[i] < 0) {
                    throw new RuntimeException("Wrong Input " + input[i]);
                }
            }
        } catch (Exception e) {
            syntex.run();
            e.printStackTrace();
            return;
        }

        CalculateLandscape ob = new CalculateLandscape();
        ob.printLandScape(input);
        long waterAmount = ob.calculateWaterAmount(input);
        System.out.println("Water Amount : " + waterAmount);

    }


    long calculateWaterAmount(int[] landscape) {

        if (!valid(landscape)) {
            return -1;
        }

        int i, j, max = Arrays.stream(landscape).max().orElse(0);
        long count = 0l;

        int length = landscape.length;
        int[][] data = loadLandScape(landscape, max);

        for (i = 0; i < max; i++) {
            j = 0;
            while (j < length) {

                int start = -1, end = -1;

                while (j < length && data[i][j] != 1) {
                    j++;
                }

                while (j < length && data[i][j] != 0) {
                    j++;
                }

                if (j < length) {
                    start = j - 1;
                }

                while (j < length && data[i][j] != 1) {
                    j++;
                }

                if (j < length) {
                    end = j;
                }

                if (start != -1 && end != -1) {
                    count += (end - start - 1);
                }
            }
        }

        return count;
    }

    boolean valid(int[] landscape) {
        if (landscape == null) {
            System.out.println("Input cannot be null.....");
            return false;
        }
        boolean present = Arrays.stream(landscape).filter(v -> v < 0).findFirst().isPresent();
        if (present) {
            System.out.println("Negative Numbers are not allowed.....");
            return false;
        }
        return true;
    }

    private int[][] loadLandScape(int[] landscape, int max) {
        int i, j, data[][] = new int[max][landscape.length];

        for (j = 0; j < landscape.length; j++) {
            for (i = 0; i < max; i++) {

                if (i < landscape[j]) {
                    data[i][j] = 1;
                } else {
                    data[i][j] = 0;
                }
            }
        }
        return data;
    }

    public void printLandScape(int[] landscape) {
        int i, j, max = Arrays.stream(landscape).max().getAsInt();
        int[][] data = loadLandScape(landscape, max);

        for (i = max - 1; i >= 0; i--) {
            for (j = 0; j < landscape.length; j++) {
                if (data[i][j] == 0) {
                    System.out.print("_ ");
                } else {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
