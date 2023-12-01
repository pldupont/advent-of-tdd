package com.pld.adventoftdd.day1;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Stream;

public class CalibrationDocumentReader {
    public int readLine(String calibrationLine) {
        String cleanedCalibrationLine = calibrationLine.toLowerCase().replaceAll("[a-z]", "");
        if (cleanedCalibrationLine.isEmpty()) {
            return 0;
        } else {
            return NumberUtils.toInt(StringUtils.left(cleanedCalibrationLine, 1) + StringUtils.right(cleanedCalibrationLine, 1), 0);
        }
    }

    public int processDocument(String document) throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(document);
        if (url == null) {
            throw new IOException(String.format("Document '%s' doesn't exist.", document));
        }
        try (Stream<String> stream = Files.lines(new File(url.toURI()).toPath())) {
            return stream.mapToInt(this::readLine).sum();
        }
    }
}
