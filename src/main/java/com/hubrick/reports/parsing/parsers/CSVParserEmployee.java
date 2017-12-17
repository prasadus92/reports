package com.hubrick.reports.parsing.parsers;

import com.hubrick.reports.model.Employee;
import com.hubrick.reports.model.Gender;
import com.hubrick.reports.parsing.CSVParser;

/**
 * Concrete implementation of {@Link CSVParser} for parsing {@Link Employee} data.
 */
public class CSVParserEmployee implements CSVParser<Employee> {

    @Override
    public Employee parse(String line) throws IllegalArgumentException {

        // Validate
        if (line == null || line.trim().isEmpty())
            throw new IllegalArgumentException("Provided CSV line is invalid, make sure there are no missing commas etc.");

        String name;
        Gender gender;
        int departmentId;
        double salary;

        try {

            String[] lineItems = line.split(",");

            name = lineItems[1].trim();
            gender = Gender.shortStringToGender(lineItems[2].trim());
            departmentId = Integer.parseInt(lineItems[0].trim());
            salary = Double.parseDouble(lineItems[3].trim());
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {

            throw new IllegalArgumentException("Provided CSV line is invalid, make sure there are no missing commas, blank values, etc.");
        }

        return new Employee(name, gender, departmentId, salary);
    }
}
