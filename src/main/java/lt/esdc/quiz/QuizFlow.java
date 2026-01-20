package lt.esdc.quiz;

import java.util.List;


public class QuizFlow {
    private int rightAnswers = 0;
    private int wrongAnswers = 0;

    private final List<String> questions;
    private final List<String> answers;
    private final List<String> correctAnswers;

    public QuizFlow(List<String> q, List<String> a, List<String> cA) {
        this.questions = q;
        this.answers = a;
        this.correctAnswers = cA;
    }

    static String formatLine(String initialStr, String el, int repeat) throws QuizException {
        Validator.validateString(initialStr);
        String repeatedEls = el.repeat(repeat);
        return String.format(repeatedEls + initialStr + repeatedEls);
    }

    static String formatLine(String initialStr) throws QuizException {
        return formatLine(initialStr, "", 0);
    }

    public void start() throws QuizException {
        Reader reader = new Reader();
        int step = this.answers.size() / this.questions.size();
        int iteration = 1;

        while (iteration <= this.questions.size()) {
            for (int q = iteration; q <= this.questions.size(); ) {
                System.out.println(formatLine(this.questions.get(iteration - 1), " 🐷 ", 3));
                if (q == iteration) break;
            }
            int startAnswers = (iteration - 1) * step;

            for (int a = startAnswers; a < this.answers.size(); a++) {
                System.out.println(formatLine(this.answers.get(a)));
                if (a == startAnswers + step - 1) break;
            }
            System.out.println();
            System.out.print(formatLine("👉 Enter your answer (Aa,Bb,Cc,Dd): "));
            String userAnswer = reader.readLine();
            String rightAnswerAtCurrentIteration = this.correctAnswers.get(iteration - 1);
            System.out.println("✅ Correct answer is: " + rightAnswerAtCurrentIteration);
            if (userAnswer.equalsIgnoreCase(rightAnswerAtCurrentIteration)) {
                System.out.println(formatLine(" It is correct!!!", " 💥 ", 1));
                this.rightAnswers++;
            } else {
                System.out.println(formatLine(" Sorry, it is wrong!", " ❌ ", 1));
                this.wrongAnswers++;
            }
            iteration++;
            System.out.println();
        }
        System.out.println("❌ Total wrong answers: " + this.wrongAnswers);
        System.out.println("✅ Total right answers: " + this.rightAnswers);
    }
}