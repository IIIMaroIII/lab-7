package lt.esdc.quiz;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Validator {
    static void validateDouble(Double... nums) throws QuizException {
        for (Double one : nums) {
            if (one == null) throw new QuizException("Numeric value is null");
            if (one.isNaN()) throw new QuizException("Numeric value is NaN");
            if (one.isInfinite()) throw new QuizException("Numeric value is " + "infinite");
        }
    }

    static void validatePath(Path p) throws QuizException {
        if (p == null) throw new QuizException("Path is null");
        if (!Files.exists(p)) throw new QuizException("File at the " + p + " " + "doesn't exist");

    }

    static <T> void validateList(List<T> list) throws QuizException {
        if (list == null) throw new QuizException("List is null");
        if (list.isEmpty()) throw new QuizException("List is empty");

    }

    static void validateString(String... str) throws QuizException {
        for (String one : str) {
            if (one == null) throw new QuizException("String is null");
            if (one.strip().isBlank()) throw new QuizException("String is empty");
        }
    }

    static List<String> skipEmptyRows(List<String> list) throws QuizException {
        validateList(list);
        List<String> res = new ArrayList<>();

        for (String one : list) {
            if (one.strip().isBlank()) continue;
            res.add(one);
        }
        return res;
    }

}