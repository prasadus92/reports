package com.hubrick.reports.reporting.reports;

import com.hubrick.reports.helpers.CSVCreator;
import com.hubrick.reports.helpers.Statistics;
import com.hubrick.reports.model.EmployeeAggregate;
import com.hubrick.reports.reporting.ReportBase;
import com.hubrick.reports.reporting.ReportManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Report implementation that calculates median age by department.
 */
public class ReportMedianEmployeeAgeByDept extends ReportBase {


    public ReportMedianEmployeeAgeByDept(Path toFile) {

        super(toFile);
    }

    @Override
    public void generate(ReportManager reportManager) throws IOException {

        String[] header = new String[]{"Department", "Median Age"};
        List<String[]> content = new ArrayList<>();

        reportManager.getEmployeesByDepartments().keySet().stream().forEach(key -> {

            // Calculate median ages
            Set<EmployeeAggregate> employees = reportManager.getEmployeesByDepartments().get(key);
            List<Integer> collect = employees.stream().map(EmployeeAggregate::getAge).collect(Collectors.toList());
            Integer[] ages = collect.toArray(new Integer[collect.size()]);
            int age = (int) Statistics.getMedian(Stream.of(ages).mapToDouble(Integer::doubleValue).toArray());

            // Append content
            content.add(new String[]{key, Integer.toString(age)});
        });

        // Generate CSV
        CSVCreator.create(getToFile(), content, header);
    }
}
