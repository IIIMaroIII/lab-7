package lt.esdc.numeric.file.statistics;

public class Main {

    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();


        while (true) {
            try {
                fileProcessor.askFile();
                fileProcessor.readFile();
                break;
            } catch (NumericFileStatisticsException ex) {
                System.err.println(ex.getMessage());
                runMenu();
                continue;
            }
        }

    }

    private static void runMenu() {
        OutputService outputService = new OutputService();
        Reader reader = new Reader();
        Parser parser = new Parser();
        outputService.printMenu();

//        int choice = parser.parseInt(reader.readLine());
    }
}