package com.hubrick.reports.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashing util.
 *
 * Recommended use: If identifiers are strings that are not standardised and are not
 * of the same length for various entities, it is beneficial to generate uniform IDs
 * of a fixed length as it significantly improves performance when dealing with
 * large datasets.
 */
public class Hasher {

    /**
     * A simple string hashing util.
     *
     * @param string string to be hashed.
     * @return fixed length SHA-256 hash string value of provided input.
     * @throws NullPointerException
     */
    public static String getHash(String string) throws NullPointerException {

        // Validate input
        if (string == null) throw new NullPointerException("Input string cannot be 'null'.");

        MessageDigest messageDigest;

        try {

            // Get digest
            messageDigest = MessageDigest.getInstance("SHA-256");

            // Pass data, also ensure that encoding is consistent
            messageDigest.update(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

            // Log error, and return
            e.printStackTrace();
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        // Compose hash string
        for (byte b : messageDigest.digest()) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }

    /**
     * Use this method specifically for hashing names.
     * Input will be lower-cased, and white spaces will be removed.
     * Users need to ensure that name/surname sequence is consistent.
     *
     * @param name Name to be hashed.
     * @return fixed length SHA-256 hash string value of provided input.
     */
    public static String nameToHash(String name) {

        // Validate input
        if (name == null) throw new NullPointerException("Input string cannot be 'null'.");

        return getHash(name.replace(" ", "").toLowerCase());
    }
}
