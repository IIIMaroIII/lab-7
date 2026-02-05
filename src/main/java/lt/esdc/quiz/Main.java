package lt.esdc.quiz;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Reader reader = new Reader();
        try {
            while (true) {
                FileProcessor fileProcessor = new FileProcessor();
                OutputService.printWelcomeToQuiz();
                String themeChoice = reader.readLine();
                switch (themeChoice) {
                    case "1" -> fileProcessor.findFile("quiz.txt");
                    case "2" -> fileProcessor.findFile("design_patterns.txt");
                    case "3" -> fileProcessor.findFile("general_science.txt");
                    case "4" -> fileProcessor.findFile("capitals.txt");
                    case "5" -> fileProcessor.findFile("js.txt");
                    case "6" -> fileProcessor.findFile("general.txt");
                    case "7" -> fileProcessor.findFile("logic.txt");
                    case "8" -> fileProcessor.findFile("phsyco.txt");
                    default -> System.exit(0);
                }

                fileProcessor.extractFileContent(fileProcessor.getPath()).categorizeFileContent();
                List<String> onlyQuestions = fileProcessor.getOnlyQuestions();
                List<String> onlyAnswers = fileProcessor.getOnlyAnswers();
                List<String> onlyCorrectAnswers = fileProcessor.getOnlyCorrectAnswers();

                QuizFlow quizFlow = new QuizFlow(onlyQuestions, onlyAnswers, onlyCorrectAnswers);
                quizFlow.start();
                OutputService.printEndingQuiz();

                String wrong =
                        quizFlow.formatLine(" Total wrong answers: " + quizFlow.getWrongAnswers()
                                , " ❌ ", 1);
                String right =
                        quizFlow.formatLine(" Total co8rrect answers: " + quizFlow.getRightAnswers(), " ✅ ", 1);
                fileProcessor.writeResults("", "", wrong, right);

                String againChoice = reader.readLine();
                if (againChoice.equalsIgnoreCase("0")) break;
                continue;
            }

        } catch (QuizException ex) {
            System.err.println(ex.getMessage());
        }

    }
}