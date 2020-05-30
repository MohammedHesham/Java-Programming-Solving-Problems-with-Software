import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

public class Birth {

    void totalBirths(CSVParser parser) {
        int totalFNames = 0;
        int totalMNames = 0;
        int totalBirths = 0;
        try {
            for (CSVRecord record : parser.getRecords()) {
                String nameBirths = record.get(2);
                totalBirths += Integer.parseInt(nameBirths);
                if (record.get(1).equals("F")) {
                    totalFNames += 1;
                } else {
                    totalMNames += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();


        }
        System.out.println("Total names " + (totalFNames + totalMNames));
        System.out.println("Number of girls names " + totalFNames);
        System.out.println("Number of boys names " + totalMNames);
        System.out.println("Total births " + totalBirths);
    }

    int getRank(int year, String name, String gender) {
        int rank = 0;
        FileResource fileResource = new FileResource("/home/hesham/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord record : fileResource.getCSVParser(false)) {
            String tableName = record.get(0);
            String tableGender = record.get(1);
            if (tableGender.equals(gender)) {
                rank++;
                if (tableName.equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    int getNumberOfBirths(int year, String name, String gender) {
        FileResource fileResource = new FileResource("/home/hesham/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord record : fileResource.getCSVParser(false)) {
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                return Integer.parseInt(record.get(2));
            }
        }
        return -1;
    }

    String getName(int year, int rank, String gender) {
        FileResource fileResource = new FileResource("/home/hesham/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int tempRank = 0;
        for (CSVRecord record : fileResource.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                tempRank++;
            }
            if (rank == tempRank) {
                return record.get(0);
            }
        }
        return "NO NAME";

    }

    void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = 0;
        FileResource fileResource = new FileResource("/home/hesham/Downloads/us_babynames/us_babynames_by_year/yob" + year + ".csv");
        CSVParser oldYearParser = fileResource.getCSVParser(false);
        for (CSVRecord record : oldYearParser) {
            if (record.get(1).equals(gender))
                rank++;
            if (record.get(0).equals(name)) {
                break;
            }
        }
        System.out.println(name + " born in " + year + " would be " + getName(newYear, rank, gender) + " if she was born in " + newYear + ".");
    }

    int yearOfHighestRank(String name, String gender) {
        DirectoryResource directoryResource = new DirectoryResource();
        int year = -1;
        int highestRank = -1;
        int totalBirth = 0;
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            for (CSVRecord record : fileResource.getCSVParser(false)) {
                if (gender.equals(record.get(1)) && record.get(0).equals(name)) {
                    if (totalBirth < Integer.parseInt(record.get(2))) {
                        String temp = file.getName().substring(3, 7);
                        int rank = getRank(Integer.parseInt(temp), name, gender);
                        if (rank != -1) {
                            if (highestRank == -1 || rank < highestRank) {
                                highestRank = rank;
                                year = Integer.parseInt(temp);
                            }
                        }
                    }
                }
            }

        }

        return year;
    }

    double getAverageRank(String name, String gender) {
        double totalRank = 0;
        int count = 0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            String temp = file.getName().substring(3, 7);

            int rank1 = getRank(Integer.parseInt(temp), name, gender);
            if (rank1 != -1) {
                totalRank += rank1;
                count++;
            }
        }


        if (count == 0)
            return 0;
        return (totalRank / count);
    }
}
