package com.hubrick.reports.model;

import java.io.Serializable;

/**
 * An aggregate of all available data that should be used for final data processing.
 */
public class EmployeeAggregate implements Serializable {

    private static final long serialVersionUID = -8115888976384880219L;

    private String uid;

    private String name;

    private Gender gender;

    private int age;

    private String department;

    private double salary;

    public EmployeeAggregate(String uid, String name) {

        this.uid = uid;
        this.name = name;
    }

    public String getUid() {

        return uid;
    }

    public String getName() {

        return name;
    }

    public Gender getGender() {

        return gender;
    }

    public int getAge() {

        return age;
    }

    public String getDepartment() {

        return department;
    }

    public double getSalary() {

        return salary;
    }

    public void setGender(Gender gender) {

        this.gender = gender;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public void setSalary(double salary) {

        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeAggregate that = (EmployeeAggregate) o;

        if (age != that.age) return false;
        if (Double.compare(that.salary, salary) != 0) return false;
        if (!uid.equals(that.uid)) return false;
        if (!name.equals(that.name)) return false;
        if (gender != that.gender) return false;
        return !(department != null ? !department.equals(that.department) : that.department != null);
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = uid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {

        return "EmployeeAggregate{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
