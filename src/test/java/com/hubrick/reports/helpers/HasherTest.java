package com.hubrick.reports.helpers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HasherTest {

    @Test(expected = NullPointerException.class)
    public void testGetHashNull() throws Exception {

        Hasher.getHash(null);
    }

    @Test
    public void testGetHash() throws Exception {

        assertEquals("Must have matching lengths", Hasher.getHash("abc").length(), Hasher.getHash("123456789").length());
        assertNotEquals("Must return different hashes", Hasher.getHash(""), Hasher.getHash(" "));
        assertNotEquals("Must return different hashes", Hasher.getHash("abc"), Hasher.getHash("Abc"));
        assertEquals("Must return identical hashes", Hasher.getHash("abc"), Hasher.getHash("abc"));
    }

    @Test(expected = NullPointerException.class)
    public void testNameToHashNull() throws Exception {

        Hasher.nameToHash(null);
    }

    @Test
    public void testNameToHash() throws Exception {

        assertNotEquals("Name/surname sequence is important", Hasher.nameToHash("Julius Glover"), Hasher.nameToHash("Glover Julius"));
        assertEquals("White spaces are ignored", Hasher.nameToHash("Julius Glover"), Hasher.nameToHash(" JuliusGlover   "));
        assertEquals("Should not be case sensitive", Hasher.nameToHash("julius glover"), Hasher.nameToHash("Julius GLOVER"));
    }
}