package lt.esdc.numeric.file.statistics;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                runMenu();
                break;
            } catch (NumericFileStatisticsException ex) {
                System.err.println(ex.getMessage());
                System.out.println("❓ Do you want to repeat?");
                continue;
            }
        }

    }

    private static void runMenu() throws NumericFileStatisticsException {
        FileProcessor fileProcessor = new FileProcessor();
        Reader reader = new Reader();
        OutputService.printMenu();

        int choice = Parser.parseInt(reader.readLine());
        switch (choice) {
            case 1 -> {
                fileProcessor.askFile();
                System.out.println("We are reading your path...");
                fileProcessor.readFile();
                System.out.println("We are evaluating the content...");
                Statistics stats = fileProcessor.calculateContent();
                System.out.println("Values are calculated");
                System.out.println("We are creating a new file for the results");
                System.out.println("Please provide a suffix for a new file or leave it empty " +
                        "(Example: '_total', '-calculated')");
                String suffix = reader.readLine();
                System.out.println("Please provide an extension for a new file or leave it by " + "default. Example: 'md', 'txt'");
                String ext = reader.readLine();
                fileProcessor.writeResults(stats, suffix, ext);

                System.out.println("Total sum: " + stats.getDoubleValuesSum());
                System.out.println("Total average: " + stats.getDoubleAverage());
            }
            default -> System.exit(0);
        }
    }
}