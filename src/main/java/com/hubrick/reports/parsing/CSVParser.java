package com.hubrick.reports.parsing;

import java.io.Serializable;

/**
 * A minimum API requirement defining interface.
 * Concrete implementations will be used by {@Link CSVProcessor}.
 */
public interface CSVParser<T extends Serializable> {

    T parse(String line) throws IllegalArgumentException;
}
