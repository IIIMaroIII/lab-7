package lt.esdc.numeric.file.statistics;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Validator {
    static void validateDouble(Double... nums) throws NumericFileStatisticsException {
        for (Double one : nums) {
            if (one == null) throw new NumericFileStatisticsException("Numeric value is null");
            if (one.isNaN()) throw new NumericFileStatisticsException("Numeric value is NaN");
            if (one.isInfinite())
                throw new NumericFileStatisticsException("Numeric value is " + "infinite");
        }
    }

    static void validatePath(Path p) throws NumericFileStatisticsException {
        if (p == null) throw new NumericFileStatisticsException("Path is null");
        if (!Files.exists(p))
            throw new NumericFileStatisticsException("File at the " + p + " " + "doesn't exist");

    }

    static <T> void validateList(List<T> list) throws NumericFileStatisticsException {
        if (list == null) throw new NumericFileStatisticsException("List is null");
        if (list.isEmpty()) throw new NumericFileStatisticsException("List is empty");

    }

    static void validateString(String... str) throws NumericFileStatisticsException {
        for (String one : str) {
            if (one == null) throw new NumericFileStatisticsException("String is null");
            if (one.strip().isBlank()) throw new NumericFileStatisticsException("String is empty");
        }
    }

    static List<String> skipEmptyRows(List<String> list) throws NumericFileStatisticsException {
        validateList(list);
        List<String> res = new ArrayList<>();

        for (String one : list) {
            if (one.strip().isBlank()) continue;
            res.add(one);
        }
        return res;
    }

}