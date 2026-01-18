package lt.esdc.numeric.file.statistics;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        Parser parser = new Parser();
        Counter counter = new Counter();
        Validator validator = new Validator();
        Writer writer = new Writer();
        OutputService outputService = new OutputService();


    }

}