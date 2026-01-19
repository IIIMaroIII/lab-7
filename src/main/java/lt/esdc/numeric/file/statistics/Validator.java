package lt.esdc.numeric.file.statistics;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Validator {
    public void validateDouble(Double n) {

    }

    public void validateDouble(Double n, int min, int max) {
    }

    public void validatePath(Path p) throws NumericFileStatisticsException {
        if (p == null) throw new NumericFileStatisticsException("Path is null");
        if (!Files.exists(p))
            throw new NumericFileStatisticsException("File at the " + p + " " + "doesn't exist");

    }

    public <T> void validateList(List<T> list) throws NumericFileStatisticsException {
        if (list == null) throw new NumericFileStatisticsException("List is null");
        if (list.isEmpty()) throw new NumericFileStatisticsException("List is empty");

    }

}