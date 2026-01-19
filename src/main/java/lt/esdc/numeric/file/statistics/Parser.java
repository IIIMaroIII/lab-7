package lt.esdc.numeric.file.statistics;

public class Parser {
    public static int parseInt(String str) throws NumericFileStatisticsException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new NumericFileStatisticsException("String -> " + str + " isn't parsable to an "
                    + "integer" + ex.getMessage(), ex);
        }
    }

    public static Double parseDouble(String str) throws NumericFileStatisticsException {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            throw new NumericFileStatisticsException("String -> " + str + " isn't parsable to an "
                    + "integer " + ex.getMessage());
        }
    }

}