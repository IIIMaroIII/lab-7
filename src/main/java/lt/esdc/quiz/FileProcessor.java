package lt.esdc.quiz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();
    private final Parser parser = new Parser();
    private Path path;

    private String regexFindQuestion = "^Q\\d+:.+\\?$";
    private String regexFindAnswer = "^[A-Z]\\).+";
    private String regexFindCorrectAnswer = "^ANSWER:.+";
    private int answersQuantity = 4;

    private List<String> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private List<String> correctAnswers = new ArrayList<>();
    private List<String> fileContent;

    public List<String> getOnlyQuestions() {
        return this.questions;
    }

    public List<String> getOnlyAnswers() {
        return this.answers;
    }

    public List<String> getOnlyCorrectAnswers() {
        return this.correctAnswers;
    }

    public List<String> getFileContent() {
        return this.fileContent;
    }

    public Path findFile(String fileName) throws QuizException {
        Validator.validateString(fileName);
        Path path = configurePath(fileName, "tasks");
        Validator.validatePath(path);
        System.out.println("FindFile -> path: " + path);
        this.path = path;
        return path;
    }

    public FileProcessor extractFileContent(Path p) throws QuizException {
        Validator.validatePath(p);
        this.fileContent = reader.readAllLines(p);
        return this;
    }

    public void categorizeFileContent() throws QuizException {
        Validator.validateList(this.fileContent);
        for (String one : this.fileContent) {
            if (one.strip().isBlank()) continue;
            if (one.matches(this.regexFindQuestion)) this.questions.add(one);
            if (one.matches(this.regexFindAnswer)) this.answers.add(one);
            if (one.matches(this.regexFindCorrectAnswer)) this.correctAnswers.add(one);
        }
        removeQuestionSeparators();
        removeCorrectAnswersSeparators();

    }

    public void removeQuestionSeparators() {
        String rgx = "^Q\\d{1,2}:";
        List<String> clearQuestions = new ArrayList<>();

        for (String one : this.getOnlyQuestions()) {
//            if (!one.matches(rgx)) continue;
            String[] arr = one.split(rgx);

            String strippedOne = arr[1].strip();
            clearQuestions.add(strippedOne);
        }
        this.questions = clearQuestions;
    }

    public void removeCorrectAnswersSeparators() {
        String rgx = "^ANSWER:";
        List<String> clearQuestions = new ArrayList<>();

        for (String one : this.getOnlyCorrectAnswers()) {
            String[] arr = one.split(rgx);
            String strippedOne = arr[1].strip();
            clearQuestions.add(strippedOne);
        }
        this.correctAnswers = clearQuestions;
    }

    public void readFile() throws QuizException {
        Validator.validatePath(this.path);
        List<String> list = reader.readAllLines(this.path);
        Validator.validateList(list);
        this.fileContent = list;
    }

    public Statistics calculateContent() throws QuizException {
        double totalSum = Counter.countSum(this.fileContent);
        double averageValue = Counter.countAverage(this.fileContent);
        return new Statistics(totalSum, averageValue);
    }

    public void writeResults(String suffix, String ext, String... args) throws QuizException {
        Validator.validatePath(this.path);
        Path newPath = createPathForNewFile(this.path, suffix, ext);
        try {
//            Files.copy(this.path, newPath);
            Files.write(newPath, List.of(args), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new QuizException("Unable to write results to -> " + newPath + ex.getMessage());
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

    public Path createPathForNewFile(Path path, String suffix, String ext) throws QuizException {
        Validator.validatePath(path);
//        try {
        if (suffix.strip().isBlank()) suffix = "_results";
        if (ext.strip().isBlank()) ext = getFileNameExt(getPath());

        String fileName = getFileNameWithoutExt(getPath());
        String newName = fileName + suffix + ext;
        Path newPath = configurePath(newName);
//            Files.deleteIfExists(newPath);
        return newPath;
//        } catch (IOException ex) {
//            throw new QuizException("Unable to create new file at the -> " + ex.getMessage());
//        }

    }

    static String getFileNameWithoutExt(Path path) throws QuizException {
        Validator.validatePath(path);
        String fullName = path.getFileName().toString();
        int indxOfDot = fullName.lastIndexOf(".");
        if (indxOfDot == -1) return fullName;
        return fullName.substring(0, indxOfDot);

    }

    static String getFileNameExt(Path path) throws QuizException {
        Validator.validatePath(path);
        String fullName = path.getFileName().toString();
        int indxOfDot = fullName.lastIndexOf(".");
        if (indxOfDot == -1) return fullName;
        return fullName.substring(indxOfDot);

    }

    static String configureDoubleString(double v, String str, int afterDot) throws QuizException {
        if (afterDot < 0 || afterDot > 4)
            throw new QuizException("Unacceptable " + "value after dot: " + afterDot);
        Validator.validateString(str);
        return String.format("⚡️ " + str + "%." + afterDot + "f%n", v);

    }

}
