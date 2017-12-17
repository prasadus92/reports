package com.hubrick.reports.parsing;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Loads and parses CSV data.
 */
public class CSVProcessor<T extends Serializable> {


    public Set<T> process(Path pathToFile, CSVParser<T> parser) throws IOException {

        HashSet<T> set = new HashSet<>();

        Stream<String> stream = Files.lines(pathToFile);
        stream.forEach(line -> set.add(parser.parse(line)));

        return set;
    }
}
