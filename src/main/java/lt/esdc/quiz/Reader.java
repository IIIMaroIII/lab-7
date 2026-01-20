package lt.esdc.quiz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private final Scanner sc;

    public Reader() {
        this.sc = new Scanner(System.in);
    }

    public String readLine() throws QuizException {
        if (!this.sc.hasNextLine()) throw new QuizException("Input is empty");
        return this.sc.nextLine();
    }

    public List<String> readAllLines(Path p) throws QuizException {
        try {
            List<String> content = Validator.skipEmptyRows(Files.readAllLines(p));
            Validator.validateList(content);
            return content;
        } catch (IOException ex) {
            throw new QuizException("Failed to read a file -> " + p + ex.getMessage());
        }
    }
}

