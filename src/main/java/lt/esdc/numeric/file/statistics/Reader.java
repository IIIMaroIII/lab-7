package lt.esdc.numeric.file.statistics;

import java.util.Scanner;

public class Reader {
    private final Scanner sc;

    public Reader() {
        this.sc = new Scanner(System.in);
    }

    public String readLine() throws NumericFileStatisticsException {
        if (!this.sc.hasNextLine()) throw new NumericFileStatisticsException("Input is empty");
        return this.sc.nextLine();
    }

}