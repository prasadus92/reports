package com.hubrick.reports.model;

import java.io.Serializable;

/**
 * A value object to hold data parsed in from a CSV file.
 */
public class Age implements Serializable {

    private static final long serialVersionUID = -6666919153114810507L;

    private String name;
    private int age;

    public Age(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public int getAge() {

        return age;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Age age1 = (Age) o;

        if (age != age1.age) return false;
        return !(name != null ? !name.equals(age1.name) : age1.name != null);

    }

    @Override
    public int hashCode() {

        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {

        return "Age{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
