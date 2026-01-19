package lt.esdc.numeric.file.statistics;

import java.nio.file.Path;

public class Writer {

    public void append(Path p, String... args) throws NumericFileStatisticsException {
        Validator.validatePath(p);

    }
}