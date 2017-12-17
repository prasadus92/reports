package com.hubrick.reports.model;

import java.io.Serializable;

/**
 * A value object to hold data parsed in from a CSV file.
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1992634475468963080L;

    private String name;


    public Department(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {

        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
