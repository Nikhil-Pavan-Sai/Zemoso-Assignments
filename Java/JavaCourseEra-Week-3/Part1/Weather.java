import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Weather
{

    private static CSVRecord coldestHourInFile(CSVParser parser)
    {

        CSVRecord coldestTemp = null;

        for(CSVRecord currRow : parser)
        {

            if(coldestTemp == null)
            {

                coldestTemp = currRow;

            }

            else
            {
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));

                double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));

                if(currTemp < lowestTemp)
                {

                    coldestTemp = currRow;

                }

            }

        }

        return coldestTemp;

    }


    private static void testColdestHourInFile()
    {

        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();

        CSVRecord lowestTemp = coldestHourInFile(parser);

        System.out.println("Coldest Temp: " + lowestTemp.get("TemperatureF") + " on " +  lowestTemp.get("DateUTC"));

    }


    private static String fileWithColdestTemperature()
    {

        File file = null;
        CSVRecord coldestTemp = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = coldestHourInFile(parser);

            if (coldestTemp == null) {

                coldestTemp = currRow;
                file = f;

            }

            else
            {

                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));

                if (currTemp < lowestTemp && currTemp > -50)
                {

                    coldestTemp = currRow;
                    file = f;

                }
            }
        }
        assert file != null;
        return file.getAbsolutePath();
    }


    private static void testFileWithColdestTemperature()
    {
        String fileWithColdestTemp = fileWithColdestTemperature();
        File f = new File(fileWithColdestTemp);
        String fileName = f.getName();

        System.out.println("Coldest day was in file " + fileName);


        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestTemp = coldestHourInFile(parser);

        System.out.println("Coldest Temperature is: " + lowestTemp.get("TemperatureF"));

        System.out.println("All the Temperatures on the coldest day were");
        CSVParser parser2 = fr.getCSVParser();
        for(CSVRecord record : parser2) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            System.out.println(temp + " at " + record.get("DateUTC"));
        }
    }



    private static CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumdity = null;
        int currHumd;
        int lowestHumd;
        for(CSVRecord currRow : parser) {
            if(lowestHumdity == null) {
                lowestHumdity = currRow;
            }

            else {
                if(!currRow.get("Humidity").equals("N/A") && !lowestHumdity.get("Humidity").equals("N/A")) {
                    currHumd = Integer.parseInt(currRow.get("Humidity"));
                    lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));

                    if(currHumd < lowestHumd) {
                        lowestHumdity = currRow;
                    }
                }
            }
        }
        return lowestHumdity;
    }

    private static void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumdity = lowestHumidityInFile(parser);

        System.out.println(lowestHumdity.get("Humidity") + " at " + lowestHumdity.get("DateUTC"));
    }

    private static CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumdity = null;
        int currHumd;
        int lowestHumd;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = lowestHumidityInFile(parser);

            if(lowestHumdity == null) {
                lowestHumdity = currRow;
            }
            else {
                int currTemp = Integer.parseInt(currRow.get("Humidity"));
                int lowestTemp = Integer.parseInt(lowestHumdity.get("Humidity"));
                if(currTemp < lowestTemp) {
                    lowestHumdity = currRow;
                }

                else {
                    if(!currRow.get("Humidity").equals("N/A") && !lowestHumdity.get("Humidity").equals("N/A")) {
                        currHumd = Integer.parseInt(currRow.get("Humidity"));
                        lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));

                        if(currHumd < lowestHumd) {
                            lowestHumdity = currRow;
                        }
                    }
                }
            }
        }
        return lowestHumdity;
    }

    private static void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println(record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    private static double averageTemperatureInFile(CSVParser parser) {
        double num = 0.0;
        double sum = 0.0;

        for(CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            sum += temp;
            num++;
        }

        return sum / num;
    }

    private static void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);

        System.out.println("average temperature is " + avg);
    }

    private static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double num = 0.0;
        double sum = 0.0;

        for(CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity >= value) {
                sum += temp;
                num++;
            }
        }

        return sum / num;
    }

    private static void testAverageTemperatureWithHighHumidityInFile() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        int humid = Integer.parseInt(br.readLine());

        double avg = averageTemperatureWithHighHumidityInFile(parser, humid);

        if(!Double.isNaN(avg)) {
            System.out.println("average temperature is " + avg);
        } else {
            System.out.println("No Temperature was found");
        }
    }




    public static void main(String[] args) throws IOException {

        testColdestHourInFile();

        testFileWithColdestTemperature();

        testLowestHumidityInFile();

        testLowestHumidityInManyFiles();

        testAverageTemperatureInFile();

        testAverageTemperatureWithHighHumidityInFile();


    }

}
