package com.hubrick.reports.helpers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetMedianNull() throws Exception {

        Statistics.getMedian(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMedianEmpty() throws Exception {

        Statistics.getMedian(new double[]{});
    }

    @Test
    public void testGetMedian() throws Exception {

        assertEquals("List with single item should return that item", 5, Statistics.getMedian(new double[]{5}), 0);
        assertEquals("List with two items should return average of those items", 0.5, Statistics.getMedian(new double[]{0, 1}), 0);

        assertEquals("List should get sorted in an ascending order before return value is calculated", 5, Statistics.getMedian(new double[]{5, 8, 0, 1, 9}), 0);

        assertEquals("List with odd number of items should return the value of middle item", 5, Statistics.getMedian(new double[]{0, 5, 120}), 0);
        assertEquals("List with even number of items should return the average value of the two items in middle", 6, Statistics.getMedian(new double[]{0, 5, 7, 80}), 0);

        assertEquals("List containing items with fractions should calculate averages correctly", 0.97, Statistics.getMedian(new double[]{0.32, 1.63}), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPercentileListNull() throws Exception {

        Statistics.getPercentile(null, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPercentileEmptyList() throws Exception {

        Statistics.getPercentile(new double[]{}, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPercentilePercentUnder() throws Exception {

        Statistics.getPercentile(new double[]{1}, -4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPercentilePercentOver() throws Exception {

        Statistics.getPercentile(new double[]{1}, 110);
    }

    @Test
    public void testGetPercentile() throws Exception {

        double[] data = new double[]{15, 20, 35, 40, 50};

        assertEquals("5th percentile is less than percent rank of the first item, must return first item",
                15.0d, Statistics.getPercentile(data, 5), 0);
        assertEquals("30th percentile equals percent rank of the second item, must return second item",
                20.0d, Statistics.getPercentile(data, 30), 0);
        assertEquals("40th percentile falls between two percent ranks, must return interpolated value",
                27.5d, Statistics.getPercentile(data, 40), 0);
        assertEquals("95th percentile is higher than the percent rank of the last item, must return last item",
                50.0d, Statistics.getPercentile(data, 95), 0);
    }
}