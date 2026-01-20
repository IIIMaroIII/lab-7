package lt.esdc.quiz;

import java.nio.file.Path;

public class Writer {

    public void append(Path p, String... args) throws QuizException {
        Validator.validatePath(p);

    }
}