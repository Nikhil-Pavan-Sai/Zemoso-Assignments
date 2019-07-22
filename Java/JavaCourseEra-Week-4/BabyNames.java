import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BabyNames
{

    static void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        CSVParser parser = fr.getCSVParser(false);

        for(CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            String gender = record.get(1);
            totalBirths += numBorn;
            if(gender.equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }

        System.out.println("Total: " + totalBirths);
        System.out.println("Boys: " + totalBoys);
        System.out.println("Girls: " + totalGirls);
    }


    static int getRank(int year,String name,String gender)
    {
        File f = new File("us_babynames_by_year/yob"+year+".csv");
        return getRankFromFile(f,name,gender);
    }


    static int getRankFromFile(File f, String name, String gender)
    {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        List<String> names = StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(1).equals(gender)).sorted(Comparator.comparingInt(record->-Integer.parseInt(record.get(2)))).
                map(record->record.get(0)).collect(Collectors.toList());

        int index = names.indexOf(name);

        return index == -1 ? index : index+1;

    }

    static String getName(int year, int rank, String gender)
    {
        File f = new File("us_babynames_by_year/yob"+year+".csv");
        return getNameFromFile(f,rank,gender);
    }

    static String getNameFromFile(File f, int rank, String gender)
    {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        List<String> names = StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(1).equals(gender)).sorted(Comparator.comparingInt((record -> -Integer.parseInt(record.get(2))))).
                map(record->record.get(0)).collect(Collectors.toList());

        String get_name = names.get(rank);

        return get_name != null ? get_name : "Name Not Found !!";


    }



    static void whatIsNameInYear(String name, int year, int newYear, String gender)
    {


        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(String.format("%s born in %d would be %s if she was born in %d",name,year,newName,newYear));

    }


    static int getYearFromFile(File f)
    {
        int year=0;
        for(char ch:f.getName().toCharArray())
        {
            if(ch>='0' && ch<='9')
                year=year*10+(ch-'0');
        }
        return year;
    }


    static int yearOfHighestRank(String name, String gender)
    {

        DirectoryResource dr = new DirectoryResource();

        return StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                min(Comparator.comparing(file->getRankFromFile(file, name, gender))).
                map(BabyNames::getYearFromFile).orElse(-1);

    }


    static double getAverageRank(String name,String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int[] ranks = StreamSupport.stream(dr.selectedFiles().spliterator(),false).
                mapToInt(file->getRankFromFile(file,name,gender)).toArray();
        double sum = 0;
        for (int rank : ranks) {
            if (rank == -1)
                return -1;
            sum += rank;
        }
        return sum/ranks.length;
    }

    static int getTotalBirthsRankedHigher(int year,String name,String gender)
    {
        FileResource fr= new FileResource();
        CSVParser parser = fr.getCSVParser();
        return StreamSupport.stream(parser.spliterator(),false).
                filter(record->record.get(1).equals(gender)).
                sorted(Comparator.comparingInt(record->-Integer.parseInt(record.get(2)))).
                takeWhile(record->!record.get(0).equals(name)).mapToInt(record->Integer.parseInt(record.get(2))).
                sum();
    }



    public static void main(String[] args) throws IOException {

        FileResource fr = new FileResource();
        totalBirths(fr);

        System.out.println("The rank is: " + getRank(2012, "Mason", "M"));


        System.out.println("The name is: " + getName(2012, 3, "M"));


        whatIsNameInYear("Isabella", 2012, 2014, "F");

        System.out.println("Highest rank from all files: " + yearOfHighestRank("Isabella", "F"));

        System.out.println("Average rank from all files: " + getAverageRank("Isabella", "F"));

        System.out.println("Total births ranked highest: " + getTotalBirthsRankedHigher(2012, "Isabella", "F"));

    }


}

