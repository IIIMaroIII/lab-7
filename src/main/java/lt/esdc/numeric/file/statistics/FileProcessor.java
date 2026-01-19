package lt.esdc.numeric.file.statistics;

import java.nio.file.Path;
import java.util.List;

public class FileProcessor {
    private final Validator validator = new Validator();
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();
    private final Parser parser = new Parser();
    private Path path;
    private List<String> fileContent;


    public FileProcessor askFile() {
        try {
            System.out.println("⚠️ Important: file has to be at the project folder");
            System.out.println("📂 Please enter a file name (file.txt, " + "file.md): ");
            String userFileName = reader.readLine();
            System.out.println("📂 Please enter a folder for a file if exists (press 'Enter' " +
                    "if not) : ");
            String userFileFolder = reader.readLine();
            Path filePath = configurePath(userFileName, userFileFolder);
            validator.validatePath(filePath);
            this.path = filePath;
        } catch (NumericFileStatisticsException ex) {
            System.err.println(ex.getMessage());
            System.out.println("📣 Try again...");
        }
        return this;
    }

    public FileProcessor readFile() throws NumericFileStatisticsException {
        try {
            List<String> list = reader.readAllLines(this.path);
            validator.validateList(list);
            this.fileContent = list;
            return this;
        } catch (NumericFileStatisticsException ex) {
            throw ex;
        }
    }

    public Path getPath() {gaa
        return this.path;
    }

    public static Path configurePath(String fileName) {
        return configurePath(fileName, "tasks");
    }

    public static Path configurePath(String fileName, String fileFolder) {
        return Path.of(System.getProperty("user.dir")).resolve(fileFolder).resolve(fileName);
    }
}
