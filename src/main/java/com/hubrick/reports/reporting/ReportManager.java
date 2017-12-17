package com.hubrick.reports.reporting;

import com.hubrick.reports.helpers.Range;
import com.hubrick.reports.model.EmployeeAggregate;

import java.io.IOException;
import java.util.*;

/**
 * Provides pre-processed data, and executes concrete report implementations.
 */
public class ReportManager {

    public static final int AGE_RANGE_INCREMENT = 10;

    private Set<EmployeeAggregate> dataSet;
    private Map<String, Set<EmployeeAggregate>> employeesByDepartments;
    private Map<Range, Set<EmployeeAggregate>> employeesByAgeRange;
    private int minAge;
    private int maxAge;

    public ReportManager(Set<EmployeeAggregate> dataSet) {

        this.dataSet = dataSet;
    }

    /**
     * Generates additional data, and commonly used groupings
     */
    private void preProcessData() {

        // Group employees by department
        if (employeesByDepartments == null) {

            employeesByDepartments = new HashMap<>();

            dataSet.stream().forEach(employee -> {

                if (!employeesByDepartments.containsKey(employee.getDepartment()))
                    employeesByDepartments.put(employee.getDepartment(), new HashSet<>());
                Set<EmployeeAggregate> employees = employeesByDepartments.get(employee.getDepartment());
                employees.add(employee);
            });
        }

        // Find out minimum, and maximum age of the employees
        if (minAge == 0) minAge = dataSet.stream().map(EmployeeAggregate::getAge).min(Integer::compare).get();
        if (maxAge == 0) maxAge = dataSet.stream().map(EmployeeAggregate::getAge).max(Integer::compare).get();

        // Group employees by age
        if (employeesByAgeRange == null) {

            employeesByAgeRange = new HashMap<>();

            // Work out age ranges
            Set<Range> ranges = new HashSet<>();
            int currTopBoundary = (int) Math.ceil((double) maxAge / (double) AGE_RANGE_INCREMENT) * AGE_RANGE_INCREMENT;

            while (currTopBoundary >= minAge) {
                Range range = new Range(currTopBoundary - AGE_RANGE_INCREMENT, currTopBoundary);
                ranges.add(range);
                employeesByAgeRange.put(range, new HashSet<>());
                currTopBoundary -= AGE_RANGE_INCREMENT;
            }

            // Group employees
            dataSet.stream().forEach(employee -> {
                for (Range range : ranges) {
                    if (range.inRange(employee.getAge())) {

                        employeesByAgeRange.get(range).add(employee);

                        break;
                    }
                }
            });
        }
    }

    /**
     * Builds reports based on supplied report list.
     *
     * @param reports List of concrete {@Link Report} instances
     * @throws IOException
     */
    public void buildReports(List<Report> reports) throws IOException {

        // Pre-process data if needed
        preProcessData();

        // Build reports
        reports.stream().forEach(report -> {
            try {
                report.generate(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Set<EmployeeAggregate> getDataSet() {

        return Collections.unmodifiableSet(dataSet);
    }

    public Map<String, Set<EmployeeAggregate>> getEmployeesByDepartments() {

        return Collections.unmodifiableMap(employeesByDepartments);
    }

    public Map<Range, Set<EmployeeAggregate>> getEmployeesByAgeRange() {

        return Collections.unmodifiableMap(employeesByAgeRange);
    }

    public int getMinAge() {

        return minAge;
    }

    public int getMaxAge() {

        return maxAge;
    }
}
