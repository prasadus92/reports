package com.hubrick.reports.helpers;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Utility class for working with statistics.
 */
public class Statistics {

    /**
     * Median is a 50th percentile value.
     *
     * @param list The array containing the data to consider.
     * @return Median value calculated from provided dataset.
     * @throws IllegalArgumentException
     */
    public static double getMedian(double[] list) throws IllegalArgumentException {

        return getPercentile(list, 50);
    }

    /**
     * Percentile implemented using 'Linear Interpolation Between Closest Ranks' method.
     * Variant used by 'MATLAB' toolset.
     *
     * @param list       The array containing the data to consider.
     * @param percentile Percentile whose value will be calculated.
     * @return Value of specified percentile calculated from provided dataset.
     * @throws IllegalArgumentException
     */
    public static double getPercentile(double[] list, int percentile) throws IllegalArgumentException {

        // Validate list
        if (list == null || list.length == 0)
            throw new IllegalArgumentException("Provided list cannot be 'null' or empty.");

        // Validate percentile
        if (percentile < 0 || percentile > 100)
            throw new IllegalArgumentException("Provided percentile value must be in the range of '0' to '100' inclusive.");

        // Sort
        Arrays.sort(list);

        int i;
        double[] percentRanks = new double[list.length];
        for (i = 0; i < list.length; i++) {
            percentRanks[i] = getPercentRank(list, i);
        }

        // Calculate percentile
        for (i = 0; i < percentRanks.length; i++) {
            if (i == 0 && percentile < percentRanks[i]) return list[0];
            if (i == percentRanks.length - 1 && percentile > percentRanks[i]) return list[percentRanks.length - 1];
            if (percentile == percentRanks[i]) return list[i];
            if (percentile > percentRanks[i] && percentile < percentRanks[i + 1]) {
                return fractionRound((list[i] + (list.length * (((percentile - percentRanks[i]) / 100d) * (list[i + 1] - list[i])))), 2);
            }
        }

        return Double.NaN;
    }

    /**
     * Calculates percent rank of the value at the given index.
     *
     * @param list  The array containing the data to consider.
     * @param index Value whose percent rank needs to be determined.
     * @return Percent rank.
     */
    public static double getPercentRank(double[] list, int index) {

        return (100d / list.length) * (index + 1 - 0.5);
    }

    public static double fractionRound(double value, int scale) {

        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
