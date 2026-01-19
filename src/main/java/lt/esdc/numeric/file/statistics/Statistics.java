package lt.esdc.numeric.file.statistics;

public class Statistics {
    private final double doubleValuesSum;
    private final double doubleAverage;

    public Statistics(double doubleValuesSum, double doubleAverage) {
        this.doubleValuesSum = doubleValuesSum;
        this.doubleAverage = doubleAverage;
    }

    public double getDoubleValuesSum() {
        return doubleValuesSum;
    }

    public double getDoubleAverage() {
        return doubleAverage;
    }
}