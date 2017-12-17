package com.hubrick.reports.model;

/**
 * Provides enumeration, and utils for gender values.
 */
public enum Gender {

    FEMALE, MALE;

    public static Gender shortStringToGender(String value) throws IllegalArgumentException {

        // Validate input
        if (value == null || value.isEmpty() || value.length() > 1)
            throw new IllegalArgumentException("Invalid input '" + value + "' given. Provided string should only contain one character. Examples: 'f', 'm'");

        switch (value.toLowerCase()) {
            case "f":
                return Gender.FEMALE;
            case "m":
                return Gender.MALE;
            default:
                return null;
        }
    }
}
