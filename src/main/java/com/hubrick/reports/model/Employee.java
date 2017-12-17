package com.hubrick.reports.model;

import java.io.Serializable;

/**
 * A value object to hold data parsed in from a CSV file.
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = -7622346231541832812L;

    private String name;

    private Gender gender;

    private int departmentId;

    private double salary;

    public Employee(String name, Gender gender, int departmentId, double salary) {

        this.name = name;
        this.gender = gender;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public String getName() {

        return name;
    }

    public Gender getGender() {

        return gender;
    }

    public int getDepartmentId() {

        return departmentId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (departmentId != employee.departmentId) return false;
        if (Double.compare(employee.salary, salary) != 0) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        return gender == employee.gender;
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + departmentId;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getSalary() {

        return salary;
    }

    @Override
    public String toString() {

        return "Employee{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }
}
