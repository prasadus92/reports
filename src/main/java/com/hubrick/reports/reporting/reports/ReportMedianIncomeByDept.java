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
 * Report implementation that calculates median salary by department.
 */
public class ReportMedianIncomeByDept extends ReportBase {

    public ReportMedianIncomeByDept(Path toFile) {

        super(toFile);
    }

    @Override
    public void generate(ReportManager reportManager) throws IOException {

        String[] header = new String[]{"Department", "Median Income"};
        List<String[]> content = new ArrayList<>();

        reportManager.getEmployeesByDepartments().keySet().stream().forEach(key -> {

            // Calculate median income
            Set<EmployeeAggregate> employees = reportManager.getEmployeesByDepartments().get(key);
            List<Double> collect = employees.stream().map(EmployeeAggregate::getSalary).collect(Collectors.toList());
            Double[] salaries = collect.toArray(new Double[collect.size()]);
            double medianIncome = Statistics.getMedian(Stream.of(salaries).mapToDouble(Double::doubleValue).toArray());

            // Append content
            content.add(new String[]{key, Double.toString(medianIncome)});
        });

        // Generate CSV
        CSVCreator.create(getToFile(), content, header);
    }
}
