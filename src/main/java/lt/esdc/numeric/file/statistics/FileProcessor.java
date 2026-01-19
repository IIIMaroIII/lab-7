package lt.esdc.numeric.file.statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileProcessor {
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();
    private final Parser parser = new Parser();
    private Path path;

    private List<String> fileContent;


    public void askFile() throws NumericFileStatisticsException {
        try {
            System.out.println("⚠️ Important: file has to be at the project folder");
            System.out.println("📂 Please enter a file name (file.txt, " + "file.md): ");
            String userFileName = reader.readLine();
            System.out.println("📂 Please enter a folder for a file if exists (press 'Enter' " +
                    "if not) : ");
            String userFileFolder = reader.readLine();
            Path filePath = configurePath(userFileName, userFileFolder);
            Validator.validatePath(filePath);
            this.path = filePath;
        } catch (NumericFileStatisticsException ex) {
            throw ex;
        }

    }

    public void readFile() throws NumericFileStatisticsException {
        Validator.validatePath(this.path);
        List<String> list = reader.readAllLines(this.path);
        Validator.validateList(list);
        this.fileContent = list;
    }

    public Statistics calculateContent() throws NumericFileStatisticsException {
        double totalSum = Counter.countSum(this.fileContent);
        double averageValue = Counter.countAverage(this.fileContent);
        return new Statistics(totalSum, averageValue);
    }

    public void writeResults(String suffix, String ext, String... args) throws NumericFileStatisticsException {
        Validator.validatePath(this.path);
        Path newPath = createPathForNewFile(this.path, suffix, ext);
        try {
            Files.copy(this.path, newPath);
            Files.write(newPath, List.of(args), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new NumericFileStatisticsException("Unable to write results to -> " + newPath + ex.getMessage());
        }


    }

    public Path getPath() {
        return this.path;
    }

    static Path configurePath(String fileName) {
        return configurePath(fileName, "tasks");
    }

    static Path configurePath(String fileName, String fileFolder) {
        return Path.of(System.getProperty("user.dir")).resolve(fileFolder).resolve(fileName);
    }

    public Path createPathForNewFile(Path path, String suffix, String ext) throws NumericFileStatisticsException {
        Validator.validatePath(path);
        try {
            if (suffix.strip().isBlank()) suffix = "_results";
            if (ext.strip().isBlank()) ext = getFileNameExt(getPath());

            String fileName = getFileNameWithoutExt(getPath());
            String newName = fileName + suffix + ext;
            Path newPath = configurePath(newName);
            Files.deleteIfExists(newPath);
            return newPath;
        } catch (IOException ex) {
            throw new NumericFileStatisticsException("Unable to create new file at the -> " + ex.getMessage());
        }

    }

    static String getFileNameWithoutExt(Path path) throws NumericFileStatisticsException {
        Validator.validatePath(path);
        String fullName = path.getFileName().toString();
        int indxOfDot = fullName.lastIndexOf(".");
        if (indxOfDot == -1) return fullName;
        return fullName.substring(0, indxOfDot);

    }

    static String getFileNameExt(Path path) throws NumericFileStatisticsException {
        Validator.validatePath(path);
        String fullName = path.getFileName().toString();
        int indxOfDot = fullName.lastIndexOf(".");
        if (indxOfDot == -1) return fullName;
        return fullName.substring(indxOfDot);

    }

    static String configureDoubleString(double v, String str, int afterDot) throws NumericFileStatisticsException {
        if (afterDot < 0 || afterDot > 4)
            throw new NumericFileStatisticsException("Unacceptable " + "value after dot: " + afterDot);
        Validator.validateString(str);
        return String.format("⚡️ " + str + "%." + afterDot + "f%n", v);

    }

}
