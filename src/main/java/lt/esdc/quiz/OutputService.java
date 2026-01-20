package lt.esdc.quiz;

import java.util.List;

public class OutputService {
    static void printWelcomeToQuiz() {
        System.out.println("============= Q U I Z =================");
        System.out.println("👋 We wanna welcome you to a small quiz");
        System.out.println("📝 to test your programming knowledge");
        System.out.println("            😄 Enjoy 😉");
        System.out.println("===== We have the following themes ====");
        System.out.println("1. Java / Programming Fundamentals (Core Java)");
        System.out.println("2. Design Patterns");
        System.out.println("3. General Science");
        System.out.println("4. World Capitals");
        System.out.println("5. JavaScript");
        System.out.println("6. General theme");
        System.out.println("7. Логика и мышление (русский)");
        System.out.println("8. Психология и мышление (русский)");
        System.out.println("0. Exit");
        System.out.println("============= Q U I Z =================");
        System.out.println("      🤓 Choose one by number 🤓");
        System.out.println();
    }

    static <T> void printList(List<T> list) throws QuizException {
        Validator.validateList(list);
        for (T one : list) {
            System.out.println(one);
        }

    }

    static void printEndingQuiz() {
        System.out.println();
        System.out.println("============= Q U I Z =================");
        System.out.println("  🎉 I hope you've had a good time 🎉");
        System.out.println(" ❓ Do you want to start over again ❓");
        System.out.println("   📝 Press any key or 0 for exit 📝");
        System.out.println("============= Q U I Z =================");
    }
}