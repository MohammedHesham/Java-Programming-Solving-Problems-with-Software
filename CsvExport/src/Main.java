import org.apache.commons.csv.*;
import edu.duke.FileResource;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new Main().tester();
    }

    private void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
//        System.out.println(countryInfo(parser, "Nauru"));
//        listExportersTwoProducts(parser, "cotton", "flowers");
//        System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }

    private String countryInfo(CSVParser csvParser, String country) {
        try {
            for (CSVRecord record : csvParser.getRecords()) {
                if (record.get("Country").equals(country)) {
                    return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return country;
    }

    private void listExportersTwoProducts(CSVParser csvParser, String exportItem1, String exportItem2) {
        try {
            for (CSVRecord record : csvParser.getRecords()) {
                if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                    System.out.println(record.get("Country"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int numberOfExporters(CSVParser csvParser, String exportItem) {
        int count = 0;
        try {
            for (CSVRecord record : csvParser.getRecords()) {
                if (record.get("Exports").contains(exportItem))
                    count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    private void bigExporters(CSVParser parser, String amount) {
        try {
            for (CSVRecord record : parser.getRecords()) {
                if (record.get("Value (dollars)").length() > amount.length()) {
                    System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
