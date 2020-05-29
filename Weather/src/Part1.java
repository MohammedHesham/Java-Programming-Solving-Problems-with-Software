import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

public class Part1 {

    private CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord coldestHourRecord = null;
        try {
            for (CSVRecord currentRecord : parser.getRecords()) {
                if (coldestHourRecord == null) {
                    coldestHourRecord = currentRecord;
                } else {
                    String temperatureF = currentRecord.get("TemperatureF");
                    String coldestTemperatureF = coldestHourRecord.get("TemperatureF");
                    double currentTemp = Double.parseDouble(temperatureF);
                    double coldestTemp = Double.parseDouble(coldestTemperatureF);
                    if (currentTemp < coldestTemp && currentTemp != -9999) {
                        coldestHourRecord = currentRecord;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coldestHourRecord;

    }

    public void testColdestHourInFile() {
        FileResource fileResource = new FileResource("weather-2014-05-01.csv");
        CSVParser csvParser = fileResource.getCSVParser();
        CSVRecord csvRecord = coldestHourInFile(csvParser);
        System.out.println(csvRecord.get("TemperatureF"));
    }

    private String fileWithColdestTemperature() {
        DirectoryResource directoryResource = new DirectoryResource();
        CSVRecord coldestRecord = null;
        String filename = null;
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            CSVRecord currentRecord = coldestHourInFile(fileResource.getCSVParser());
            if (coldestRecord == null) {
                coldestRecord = currentRecord;
                filename = file.getName();
            } else {
                String temperatureF = currentRecord.get("TemperatureF");
                String coldestTemperatureF = coldestRecord.get("TemperatureF");
                double currentTemp = Double.parseDouble(temperatureF);
                double coldestTemp = Double.parseDouble(coldestTemperatureF);
                if (coldestTemp > currentTemp) {
                    filename = file.getName();
                    coldestRecord = currentRecord;
                }
            }
        }
        System.out.println(String.format("Lowest TemperatureF was %s at %s", coldestRecord.get("TemperatureF"),coldestRecord.get("DateUTC")));

        return filename;
    }

    private CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;
        try {
            for (CSVRecord currentRecord : parser.getRecords()) {
                if (lowestHumidityRecord == null) {
                    lowestHumidityRecord = currentRecord;
                } else {
                    String humidity = currentRecord.get("Humidity");
                    if (humidity.equals("N/A"))
                        continue;
                    lowestHumidityRecord = lowestHumidityBetween2Records(lowestHumidityRecord, currentRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lowestHumidityRecord;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("weather-2014-07-22.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(String.format("Lowest Humidity was %s at %s", csv.get("Humidity"), csv.get("DateUTC")));
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println(String.format("Lowest Humidity in all files was %s at %s", record.get("Humidity"), record.get("DateUTC")));

    }

    public void testAverageTemperatureInFile() {
        FileResource fileResource = new FileResource("2013/weather-2013-08-10.csv");
        double averageTemperatureInFile = averageTemperatureInFile(fileResource.getCSVParser());
        System.out.println("Average temperature in file is " + averageTemperatureInFile);
    }

    private double averageTemperatureInFile(CSVParser parser) {

        double totalTemp = 0;
        int count = 0;
        try {
            for (CSVRecord record : parser.getRecords()) {
                String temperatureF = record.get("TemperatureF");
                double temp = Double.parseDouble(temperatureF);
                count++;
                if (temp != -9999) {
                    totalTemp += temp;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (count == 0)
            return 0;

        return (totalTemp / count);
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource directoryResource = new DirectoryResource();
        CSVRecord lowestHumidityRecord = null;
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            CSVRecord currentRecord = lowestHumidityInFile(fileResource.getCSVParser());
            if (lowestHumidityRecord == null) {
                lowestHumidityRecord = currentRecord;
            } else {
                lowestHumidityRecord = lowestHumidityBetween2Records(lowestHumidityRecord, currentRecord);
            }
        }

        return lowestHumidityRecord;
    }

    private CSVRecord lowestHumidityBetween2Records(CSVRecord lowestHumidityRecord, CSVRecord currentRecord) {
        String humidity = currentRecord.get("Humidity");
        String lowestHumidity = lowestHumidityRecord.get("Humidity");
        double currentHumidity = Double.parseDouble(humidity);
        double lowestHumidityD = Double.parseDouble(lowestHumidity);
        if (lowestHumidityD > currentHumidity) {
            lowestHumidityRecord = currentRecord;
        }
        return lowestHumidityRecord;
    }

    public void testFileWithColdestTemperature() {
        System.out.println(fileWithColdestTemperature());
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0;
        int count = 0;
        try {
            for (CSVRecord record : parser.getRecords()) {
                String humidity = record.get("Humidity");
                if (humidity.equals("N/A"))
                    continue;
                String temperatureF = record.get("TemperatureF");
                double temp = Double.parseDouble(temperatureF);
                if (Double.parseDouble(humidity) >= value && temp != -9999) {
                    count++;
                    totalTemp += temp;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (count == 0)
            return 0.0;

        return (totalTemp / count);

    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fileResource = new FileResource("2013/weather-2013-09-02.csv");
        double av = averageTemperatureWithHighHumidityInFile(fileResource.getCSVParser(), 80);
        if (av == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + av);
        }
    }
}
