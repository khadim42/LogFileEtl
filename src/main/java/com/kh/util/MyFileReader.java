package com.kh.util;


import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.stream.Stream;

public class MyFileReader {

    public static void read(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                LogLineHandler.processJsonLine(line);
            });
        }
    }
}
