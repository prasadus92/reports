# Files

There are three CSV files in `data` directory:

 * `departments.csv`  - list of departments
 * `employees.csv`    - first column contains position of department in alphabetically sorted department list, followed by employee name and salary
 * `ages.csv`         - first column contains employee name, followed by age

# Challenge

Write a Java program that will generate the following reports in corresponding files in `report-results` directory:

 * `income-by-department.csv` - median income by department
 * `income-95-by-department.csv` - 95-percentile income by department
 * `income-average-by-age-range.csv` - average income by age ranges with ten year increments
 * `employee-age-by-department.csv` - median employee age by department
 
Reports must be generated in a CSV format with header columns.
 
## Conditions
  
 * Code should be compilable with Oracle JDK 1.8 and run with path to directory containing data files as the first parameter;
 * Only libraries that are part of Oracle Java Runtime are allowed in production code;
 * provide the solution source code either as zip or as a link to the code repository
 * time cap is hard to quantify, as a rough guideline 3h should be enough but please spend as much time as you need to come up with a solution you are satisfied with and confident about
 
# Test/Build/Run...

## Test
To run tests, execute the following command:

```
$ gradle clean check test
```

## Build
To build the application, execute the following command:

```
$ gradle build
```

## Run
To run as standalone application with default details, execute the following command:

```
$ gradle run
```

Or with specific data directory:

```
$ gradle run -PappArgs=<SOURCE_DIR>
```

Or with specific data, and results directories:

```
$ gradle run -PappArgs=<SOURCE_DIR>,<RESULT_DIR>
```

Or with specific results directory:

```
$ gradle run -PappArgs=,<RESULT_DIR>
```

com.hubrick.reports.Application can also be run as a standalone jar, however,
data source directory will have to be explicitly specified as a first parameter as follows:

```
$ java -jar build/libs/reports-0.0.1-SNAPSHOT.jar <SOURCE_DIR>
```