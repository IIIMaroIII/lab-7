package lt.esdc.numeric.file.statistics;

import java.util.List;

public class Counter {

    public static double countSum(List<String> list) throws NumericFileStatisticsException {
        Validator.validateList(list);
        double result = 0.00;

        for (String one : list) {
            Validator.validateString(one);
            result += Parser.parseDouble(one);
        }

        return result;
    }

    public static double countAverage(List<String> list) throws NumericFileStatisticsException {
        Double totalSum = Counter.countSum(list);
        return totalSum / list.size();

    }

}