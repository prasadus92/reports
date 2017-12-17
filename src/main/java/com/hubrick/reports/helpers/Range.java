package com.hubrick.reports.helpers;


/**
 * A convenience class for working with ranges.
 */
public class Range {

    private double start;
    private double end;

    /**
     * @param start Value to be used as the beginning of the range
     * @param end   Value to be used as the end of the range
     * @throws IllegalArgumentException
     */
    public Range(double start, double end) throws IllegalArgumentException {

        // Validate input
        if (start >= end) throw new IllegalArgumentException("'start' value has to be less than 'end' value.");

        this.start = start;
        this.end = end;
    }

    public double getStart() {

        return start;
    }

    public double getEnd() {

        return end;
    }

    /**
     * Checks if the value provided is withing the range.
     * <p>
     * Note: 'end' value will be inclusive, while 'start' value will not.
     *
     * @param value A value to be evaluated.
     * @return A boolean value 'true|false'.
     */
    // TODO Improve code to specify whether 'start|end' should be inclusive
    public boolean inRange(double value) {

        return value > start && value <= end;
    }

    @Override
    public String toString() {

        return "Range{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
