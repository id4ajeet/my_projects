package com.ajeet.fxpro;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class CalculateLandscapeTest {

    private CalculateLandscape obj = new CalculateLandscape();

    @Test
    public void nullInputTest() {
        long waterAmount = obj.calculateWaterAmount(null);
        Assert.assertEquals(-1, waterAmount);
    }

    @Test
    public void emptyInputTest() {
        long waterAmount = obj.calculateWaterAmount(new int[]{});
        Assert.assertEquals(0, waterAmount);
    }

    @Test
    public void negativeInputTest() {
        long waterAmount = obj.calculateWaterAmount(new int[]{5, 2, 3, -4, 5, 4, 0, 3, 1});
        Assert.assertEquals(-1, waterAmount);
    }

    @Test
    public void maxCapacityTest() {
        int edgeData[] = new int[3200];
        edgeData[0] = 3200;
        edgeData[3199] = 3200;

        long waterAmount = obj.calculateWaterAmount(edgeData);

        int expected = 3200 * (3200 - 2);
        Assert.assertEquals(expected, waterAmount);
    }

    @Test
    public void bothEdgeHighTest() {
        int input[] = new int[]{3200, 1, 3, 4, 3, 2, 1, 0, 3200};

        long waterAmount = obj.calculateWaterAmount(input);

        int expected = 3199 + 3197 + 3196 + 3197 + 3198 + 3199 + 3200;
        Assert.assertEquals(expected, waterAmount);
    }

    /**
     * <pre>
     * _ _ _ | _ _ _ _ _
     * _ _ | | | _ _ _ _
     * _ _ | | | | _ _ _
     * _ | | | | | | _ _
     * </pre>
     */
    @Test
    public void noWaterTest() {
        int input[] = new int[]{0, 1, 3, 4, 3, 2, 1, 0, 0};

        obj.printLandScape(input);

        long waterAmount = obj.calculateWaterAmount(input);

        Assert.assertEquals(0, waterAmount);
    }

    /**
     * <pre>
     * | _ _ _ | _ _ _ _
     * | _ _ | | | _ _ _
     * | _ | | | | _ | _
     * | | | | | | _ | _
     * | | | | | | _ | |
     * </pre>
     */
    @Test
    public void existsWaterInput1Test() {
        int input[] = new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1};

        obj.printLandScape(input);

        long waterAmount = obj.calculateWaterAmount(input);

        Assert.assertEquals(9, waterAmount);
    }

    /**
     * <pre>
     * | _ _ _ _ _ _ _ _
     * | _ _ _ _ _ _ _ |
     * | _ _ _ _ _ _ _ |
     * | _ _ _ _ _ _ _ |
     * | _ _ _ _ _ _ _ |
     * | _ _ _ _ _ _ _ |
     * </pre>
     */
    @Test
    public void existsWaterInput2Test() {
        int input[] = new int[]{6, 0, 0, 0, 0, 0, 0, 0, 5};

        obj.printLandScape(input);

        long waterAmount = obj.calculateWaterAmount(input);

        int expected = 5 * 7;

        Assert.assertEquals(expected, waterAmount);
    }
}
