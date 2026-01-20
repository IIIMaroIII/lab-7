package lt.esdc.quiz;

public class QuizException extends Exception {
    public QuizException(String msg) {
        super(msg);
    }

    public QuizException(String msg, Throwable ex) {
        super(msg, ex);
    }
}