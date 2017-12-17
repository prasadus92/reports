package com.hubrick.reports.parsing;

import com.hubrick.reports.model.Age;
import com.hubrick.reports.model.Department;
import com.hubrick.reports.model.Employee;
import com.hubrick.reports.model.Gender;
import com.hubrick.reports.parsing.parsers.CSVParserAge;
import com.hubrick.reports.parsing.parsers.CSVParserDepartment;
import com.hubrick.reports.parsing.parsers.CSVParserEmployee;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CSVParserTest {
    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserAgeMissingFields() throws Exception {

        new CSVParserAge().parse("Opal Ballard 23");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserAgeBadFormat() throws Exception {

        new CSVParserAge().parse("23, Opal Ballard");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserAgeNull() throws Exception {

        new CSVParserAge().parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserAgeEmpty() throws Exception {

        new CSVParserAge().parse(" ");
    }

    @Test
    public void testCSVParserAge() throws Exception {

        Age age = new CSVParserAge().parse("Opal Ballard, 23");

        assertEquals("Must have correct name", "Opal Ballard", age.getName());
        assertEquals("Must have correct age", 23, age.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserDepartmentNull() throws Exception {

        new CSVParserDepartment().parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserDepartmentEmpty() throws Exception {

        new CSVParserDepartment().parse(" ");
    }

    @Test
    public void testCSVParserDepartment() throws Exception {

        Department department = new CSVParserDepartment().parse("Information Technology");

        assertEquals("Must have correct name", "Information Technology", department.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserEmployeeMissingFields() throws Exception {

        new CSVParserEmployee().parse("5,Deanna Rice f,3680.00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserEmployeeBadFormat() throws Exception {

        new CSVParserEmployee().parse("Deanna Rice,5, 3680.00,f");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserEmployeeNull() throws Exception {

        new CSVParserEmployee().parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCSVParserEmployeeEmpty() throws Exception {

        new CSVParserEmployee().parse(" ");
    }

    @Test
    public void testCSVParserEmployee() throws Exception {

        Employee employee = new CSVParserEmployee().parse("5,Deanna Rice,f ,3680.00");

        assertEquals("Must have correct name", "Deanna Rice", employee.getName());
        assertEquals("Must have correct gender", Gender.FEMALE, employee.getGender());
        assertEquals("Must have correct department ID", 5, employee.getDepartmentId());
        assertEquals("Must have correct salary", 3680.00d, employee.getSalary(), 0);
    }
}