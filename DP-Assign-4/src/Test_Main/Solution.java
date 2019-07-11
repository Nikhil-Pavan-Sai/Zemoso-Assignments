package Test_Main;

/*A new cab service called Utoo is in town, which operates in Hyderabad, Chennai and Bengaluru.
Write a program which shows them the rate chart for different cabs.





Classes : ConcreteClasses.Cab, ConcreteClasses.City

Sedan is only present in Bengaluru and Chennai.

SUV is only present in Hyderabad and Bengaluru.

Types of cabs : Micro, Mini, Sedan, SUV


Use abstract factory pattern.*/


import ConcreteClasses.CabInterface;
import FactoryClasses.CabFactory;
import FactoryClasses.CityFactory;
import FactoryClasses.RateFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Solution
{



    public static void main(String[] args) throws IOException
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] hyd = {"SUV", "Micro", "Mini"};

        String[] bang = {"SUV", "Sedan", "Micro", "Mini"};

        String[] chen = {"Sedan", "Micro", "Mini"};

        System.out.println("Enter the city in which you are ! (Hyd / Bang / Chen) ?");

        String cityName = br.readLine();

        System.out.println("Enter the cab type you want to ride ! (SUV / Sedan / Micro / Mini) ?");

        String cabType = br.readLine();

        boolean bool = false;

        if(cityName.equals("Hyderabad") || cityName.equals("Hyd")) {

            for (String s : hyd) {
                if (s.equals(cabType)) {

                    System.out.println("Enter pickup and drop locations: ");

                    String pickup = br.readLine();
                    String drop = br.readLine();

                    CabInterface rateObj = ImplementFactory.getRequestedCab(new RateFactory(pickup, drop));

                    execute(cabType, cityName);
                    System.out.println("Cab Driver: " + driverName());
                    cabNumber();
                    System.out.println("Contact number: " + (int)contactNumber());

                    System.out.println("Estimated Fare is: Rs. " + rateObj);


                    bool = true;
                    break;
                }


            }

        }

        if(cityName.equals("Bangalore") || cityName.equals("Bang"))
        {
            for (String s : bang)
            {
                if (s.equals(cabType))
                {

                    System.out.println("Enter pickup and drop locations: ");

                    String pickup = br.readLine();
                    String drop = br.readLine();

                    CabInterface rateObj = ImplementFactory.getRequestedCab(new RateFactory(pickup, drop));
                    execute(cabType, cityName);
                    System.out.println("Cab Driver: " + driverName());
                    cabNumber();
                    System.out.println("Contact number: " + (int)contactNumber());

                    System.out.println("Estimated Fare is: Rs. " + rateObj);

                    bool = true;
                    break;
                }

             }

        }


        if(cityName.equals("Chennai") || cityName.equals("Chen"))
        {
            for (String s : chen)
            {
                if (s.equals(cabType))
                {

                    System.out.println("Enter pickup and drop locations: ");

                    String pickup = br.readLine();
                    String drop = br.readLine();

                    CabInterface rateObj = ImplementFactory.getRequestedCab(new RateFactory(pickup, drop));

                    execute(cabType, cityName);
                    System.out.println("Cab Driver: " + driverName());
                    cabNumber();
                    System.out.println("Contact number: " + (int)contactNumber());

                    System.out.println("Estimated Fare is: Rs. " + rateObj);

                    bool = true;
                    break;
                }
            }

        }


        if (!bool)
            System.out.println("The cab type you are requesting is not available in your location !");


        System.out.println("\n\nHappy Riding ! ;)");



    }





    private static void execute(String cab, String city)
    {

        CabInterface cabObj = ImplementFactory.getRequestedCab(new CabFactory(cab));

        CabInterface cityObj = ImplementFactory.getRequestedCab(new CityFactory(city));

        System.out.println("Cab is: " + cabObj);

        System.out.println("You are in city: " + cityObj);

    }

    private static void cabNumber()
    {

        Random rand = new Random();

        double randomValue = 0 + (9999) * rand.nextDouble();

        System.out.println("The cab number is: " + (int)randomValue);

    }

    private static String driverName()
    {

        final String name = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        Random rand = new Random();

        StringBuilder builder = new StringBuilder();

        while(builder.toString().length() == 0)
        {

            int length = rand.nextInt(5)+5;

            for(int i = 0; i < length; i++)
            {

                builder.append(name.charAt(rand.nextInt(name.length())));

            }
        }

        return builder.toString();
    }

    private static double contactNumber()
    {

        return (int)((Math.random() * 9000000)+1000000);


    }

}
