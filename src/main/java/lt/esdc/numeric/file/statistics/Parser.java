package lt.esdc.numeric.file.statistics;

public class Parser {
    public int parseInt(String str) throws NumericFileStatisticsException {
        if (str == null)
            throw new NumericFileStatisticsException("String -> " + str + " is  " + "null");
        if (str.strip().isBlank())
            throw new NumericFileStatisticsException("String -> " + str + " is  " + "empty");
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new NumericFileStatisticsException("String -> " + str + " isn't parsable to an "
                    + "integer" + ex.getMessage(), ex);

        }
    }
}