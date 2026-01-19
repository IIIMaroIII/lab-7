package lt.esdc.numeric.file.statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private final Scanner sc;

    public Reader() {
        this.sc = new Scanner(System.in);
    }

    public String readLine() throws NumericFileStatisticsException {
        if (!this.sc.hasNextLine()) throw new NumericFileStatisticsException("Input is empty");
        return this.sc.nextLine();
    }

    public List<String> readAllLines(Path p) throws NumericFileStatisticsException {
        try {
            List<String> content = Validator.skipEmptyRows(Files.readAllLines(p));
            Validator.validateList(content);
            System.out.println("Heres a list: " + Arrays.toString(content.toArray()));
            return content;
        } catch (IOException ex) {
            throw new NumericFileStatisticsException("Failed to read a file -> " + p + ex.getMessage());
        }
    }
}

