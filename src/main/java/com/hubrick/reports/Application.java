package com.hubrick.reports;

import com.hubrick.reports.helpers.Hasher;
import com.hubrick.reports.model.Age;
import com.hubrick.reports.model.Department;
import com.hubrick.reports.model.Employee;
import com.hubrick.reports.model.EmployeeAggregate;
import com.hubrick.reports.parsing.CSVProcessor;
import com.hubrick.reports.parsing.parsers.CSVParserAge;
import com.hubrick.reports.parsing.parsers.CSVParserDepartment;
import com.hubrick.reports.parsing.parsers.CSVParserEmployee;
import com.hubrick.reports.reporting.Report;
import com.hubrick.reports.reporting.ReportManager;
import com.hubrick.reports.reporting.reports.Report95PercentileIncomeByDept;
import com.hubrick.reports.reporting.reports.ReportIncomeByAgeRanges;
import com.hubrick.reports.reporting.reports.ReportMedianEmployeeAgeByDept;
import com.hubrick.reports.reporting.reports.ReportMedianIncomeByDept;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    public static void main(String[] args) {

        try {

            // Handle source directory
            String dataDir = getSrcDir(args);

            // If source directory doesn't exist, abort
            if (!Files.exists(Paths.get(dataDir))) {
                System.out.println("ERROR: Please provide a valid data source directory as the first argument. Provided directory doesn't exist!");
                return;
            }

            // Get paths to source files
            Path pathToAgesFile = Paths.get(dataDir + "ages.csv");
            Path pathToDepartmentsFile = Paths.get(dataDir + "departments.csv");
            Path pathToEmployeesFile = Paths.get(dataDir + "employees.csv");


            // Handle results directory
            String resultsDir = getResultsDir(args);


            // Load and parse age data
            Map<String, Age> ages = ageSetToMap(new CSVProcessor<Age>().process(pathToAgesFile, new CSVParserAge()));

            // Load and parse employee data
            Map<String, Employee> employees = employeeSetToMap(new CSVProcessor<Employee>().process(pathToEmployeesFile, new CSVParserEmployee()));

            // Load and parse department data
            Set<Department> departmentsSet = new CSVProcessor<Department>().process(pathToDepartmentsFile, new CSVParserDepartment());

            // Sort department data, and convert to List
            List<Department> departments = departmentsSet.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(Collectors.toList());

            // Construct aggregate data
            Set<EmployeeAggregate> aggregates = constructAggregates(employees, ages, departments);


            // Generate reports
            ReportManager reportManager = new ReportManager(aggregates);
            reportManager.buildReports(new ArrayList<Report>() {{
                add(new ReportMedianIncomeByDept(Paths.get(resultsDir + "income-by-department.csv")));
                add(new Report95PercentileIncomeByDept(Paths.get(resultsDir + "income-95-by-department.csv")));
                add(new ReportIncomeByAgeRanges(Paths.get(resultsDir + "income-average-by-age-range.csv")));
                add(new ReportMedianEmployeeAgeByDept(Paths.get(resultsDir + "employee-age-by-department.csv")));
            }});


            System.out.println("SUCCESS: Reports have been generated and can be found under directory:");
            System.out.println("> " + resultsDir);

        } catch (IOException | IllegalArgumentException e) {

            e.printStackTrace();
        }

    }

    private static String getResultsDir(String[] args) throws IOException {

        String dir = args.length == 2 && args[1] != null && !args[1].isEmpty() ? args[1] : "report-results";

        // Add separator
        dir += SEPARATOR;

        // Clean up double separators
        dir = dir.replace(SEPARATOR + SEPARATOR, SEPARATOR);

        // Ensure the directory exists
        Path resultsDirPath = Paths.get(dir);
        if (!Files.exists(resultsDirPath)) {
            Files.createDirectories(resultsDirPath);
        }

        return dir;
    }

    public static String getSrcDir(String[] args) {

        String dir = args.length >= 1 && args[0] != null && !args[0].isEmpty()
                ? args[0] : Application.class.getResource("data").getPath();

        // Add separator
        dir += SEPARATOR;

        // Clean up double separators
        dir = dir.replace(SEPARATOR + SEPARATOR, SEPARATOR);

        return dir;
    }

    private static Set<EmployeeAggregate> constructAggregates(Map<String, Employee> employees, Map<String, Age> ages, List<Department> departments) {

        HashSet<EmployeeAggregate> set = new HashSet<>();

        employees.keySet().stream().forEach(key -> {

            Employee employee = employees.get(key);
            EmployeeAggregate aggregate = new EmployeeAggregate(key, employee.getName());

            aggregate.setGender(employee.getGender());
            aggregate.setSalary(employee.getSalary());
            aggregate.setAge(ages.get(aggregate.getUid()).getAge());

            int departmentIndex = employee.getDepartmentId() - 1;
            if (departmentIndex >= 0 && departmentIndex < departments.size()) {

                aggregate.setDepartment(departments.get(departmentIndex).getName());
            }

            set.add(aggregate);
        });

        return set;
    }

    public static Map<String, Age> ageSetToMap(Set<Age> set) {

        HashMap<String, Age> result = new HashMap<>();
        set.stream().forEach(item -> result.put(Hasher.nameToHash(item.getName()), item));

        return result;
    }

    public static Map<String, Employee> employeeSetToMap(Set<Employee> set) {

        HashMap<String, Employee> result = new HashMap<>();
        set.stream().forEach(item -> result.put(Hasher.nameToHash(item.getName()), item));

        return result;
    }
}