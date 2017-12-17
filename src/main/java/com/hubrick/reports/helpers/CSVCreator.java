package com.hubrick.reports.helpers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * A util for generating CSV files.
 */
public class CSVCreator {

    public static void create(Path path, List<String[]> content, String[] header) throws IOException {

        ArrayList<String> lines = new ArrayList<>();

        // Add header
        lines.add(String.join(",", header));

        // Add lines
        content.stream().forEach(line -> {
            lines.add(String.join(",", line));
        });

        // Write to file
        Files.write(path, lines);
    }
}
