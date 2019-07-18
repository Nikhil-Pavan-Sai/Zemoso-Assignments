import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CheckExport
{

    private static String countryInfo(CSVParser parser, String country)
    {

        for (CSVRecord record: parser)
        {

            String myCountry = record.get("Country");
            if(myCountry.contains(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String info = myCountry + ": " + exports + ": " + value;
                return info;
            }
        }

        return "NOT FOUND";

    }


    private static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {

        for (CSVRecord record: parser)
        {

            String exports = record.get("Exports");
            String cntry = record.get("Country");

            if(exports.contains(exportItem1) && exports.contains(exportItem2))
            {

                System.out.println(cntry);

            }
        }

    }


    private static int numberOfExporters(CSVParser parser, String exportItem)
    {


        int count = 0;

        for (CSVRecord record : parser)
        {

            String export = record.get("Exports");

            System.out.println(export);

            if(export.contains(exportItem))
            {
                count++;
            }

        }



        return count;

    }


    private static void bigExporters(CSVParser parser, String amount)
    {

        for(CSVRecord record : parser)
        {

            String value = record.get("Value (dollars)");
            String country = record.get("Country");

            if(value.length() > amount.length())
            {

                System.out.println(country + ": " + value);

            }

        }

    }

    private static void tester()
    {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        System.out.println("Country's info: " + countryInfo(parser,"Germany"));

        parser = fr.getCSVParser();

        listExportersTwoProducts(parser, "gold", "diamonds");

        parser = fr.getCSVParser();

        System.out.println("gold: " + numberOfExporters(parser, "gold"));

        parser = fr.getCSVParser();

        bigExporters(parser, "$999,999,999,999");

    }

    public static void main(String[] args)
    {

        tester();

    }


}