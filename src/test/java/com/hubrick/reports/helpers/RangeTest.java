package com.hubrick.reports.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTest {


    @Test(expected = IllegalArgumentException.class)
    public void testRangeInvalidInput() throws Exception {

        new Range(1d, 1d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeInvalidInputNegative() throws Exception {

        new Range(-1d, -2d);
    }

    @Test
    public void testGetters() throws Exception {

        Range range = new Range(-10d, 56.32d);
        assertEquals("Should be able to retrieve correct 'start' value", -10d, range.getStart(), 0);
        assertEquals("Should be able to retrieve correct 'end' value", 56.32d, range.getEnd(), 0);
    }

    @Test
    public void testInRange() throws Exception {

        Range range = new Range(5d, 32d);

        // Test API constraints
        assertFalse("Should not include start value", range.inRange(5d));
        assertTrue("Should include end value", range.inRange(32d));

        // Test other values
        assertFalse(range.inRange(-1d));
        assertFalse(range.inRange(4.99999999d));
        assertFalse(range.inRange(32.00000001d));
        assertFalse(range.inRange(100d));
        assertTrue(range.inRange(5.00000001d));
        assertTrue(range.inRange(10d));
        assertTrue(range.inRange(31.99999999d));
    }
}