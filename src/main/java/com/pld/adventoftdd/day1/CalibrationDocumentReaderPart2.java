package com.pld.adventoftdd.day1;

import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CalibrationDocumentReaderPart2 {
    private static final Map<String, String> DIGITS_MAP = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9",
            "zero", "0"
    );
    public static final String FIRST_DIGIT_REGEX = "^.*?(one|two|three|four|five|six|seven|eight|nine|\\d).*$";
    public static final String LAST_DIGIT_REGEX = "^.*(one|two|three|four|five|six|seven|eight|nine|\\d).*$";

    public int readLine(String calibrationLine) {
        String cleanedCalibrationLine = cleanAndReplaceLine(calibrationLine.toLowerCase());
        return NumberUtils.toInt(cleanedCalibrationLine, 0);
    }

    public int processDocument(String document) throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(document);
        if (url == null) {
            throw new IOException(String.format("Document '%s' doesn't exist.", document));
        }
        try (Stream<String> stream = Files.lines(new File(url.toURI()).toPath())) {
            return stream
//                    .map(this::debug)
                    .mapToInt(this::readLine)
//                    .map(this::debug)
                    .sum();
        }
    }

    private String debug(String i) {
        System.out.print(i + "," + cleanAndReplaceLine(i.toLowerCase()) + ",");
        return i;
    }

    private int debug(int i) {
        System.out.println(i);
        return i;
    }

    public String cleanAndReplaceLine(String calibrationLine) {
        String firstDigit = getDigit(FIRST_DIGIT_REGEX, calibrationLine);

        String lastDigit = getDigit(LAST_DIGIT_REGEX, calibrationLine);
        return firstDigit + lastDigit;
    }

    private static String getDigit(String regex, String calibrationLine) {
        Pattern digitPatter = Pattern.compile(regex);
        Matcher matcher = digitPatter.matcher(calibrationLine);
        String digit = "0";
        if (matcher.matches()) {
            digit = matcher.group(1);
            if (!NumberUtils.isDigits(digit)) {
                digit = DIGITS_MAP.get(digit);
            }
        }
        return digit;
    }
}
