package com.hubrick.reports.parsing.parsers;

import com.hubrick.reports.model.Age;
import com.hubrick.reports.parsing.CSVParser;

/**
 * Concrete implementation of {@Link CSVParser} for parsing {@Link Age} data.
 */
public class CSVParserAge implements CSVParser<Age> {

    @Override
    public Age parse(String line) throws IllegalArgumentException {

        // Validate
        if (line == null || line.trim().isEmpty())
            throw new IllegalArgumentException("Provided CSV line is invalid, make sure there are no missing commas etc.");

        String name;
        int age;

        String[] lineItems = line.split(",");

        try {

            name = lineItems[0].trim();
            age = Integer.parseInt(lineItems[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {

            throw new IllegalArgumentException("Provided CSV line is invalid, make sure there are no missing commas etc.");
        }

        return new Age(name, age);
    }
}
