package lt.esdc.quiz;

public class Parser {
    public static int parseInt(String str) throws QuizException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new QuizException("String -> " + str + " isn't parsable to an " + "integer" + ex.getMessage(), ex);
        }
    }

    public static Double parseDouble(String str) throws QuizException {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            throw new QuizException("String -> " + str + " isn't parsable to an " + "integer " + ex.getMessage());
        }
    }

}