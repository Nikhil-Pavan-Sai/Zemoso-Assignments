import java.util.regex.*;
import java.util.Scanner;
import java.io.*;

public class Part3
{

  public static boolean twoOccurrences(String a, String b)
  {

    Pattern pattern = Pattern.compile(b);
    Matcher matcher = pattern.matcher(a);


    int count = 0;
    while (matcher.find()) {
      count++;
    }

    if (count == 2) {
      return true;
    }
    else
      return false;

  }


  public static void testing()
  {

    String[] test_cases_a = {"the",
    "abcd",
    "car",
    "used",
    "before",
    "nikhil",
    "well"};

    String[] test_cases_b = {"the usefulness is the most",
    "abcdgfhghxkljwdabcd",
    "my car is the worst car ever",
    "I never used it",
    "it was fair before",
    "nikhil is talented, nikhil is smart",
    "well said !"};

    for(int i=0; i<test_cases_a.length; i++)
    {

      System.out.println(twoOccurrences(test_cases_b[i], test_cases_a[i]) ? "True, --" +  test_cases_a[i] + " -- is repeated twice" : "False, --" + test_cases_a[i] + " -- There is no such string repeated twice");

      String result = lastPart(test_cases_a[i], test_cases_b[i]);

      System.out.println(result != "" ? result : test_cases_a[i]);

    }

  }


  public static String lastPart(String a,String b)
  {
    boolean isFound = b.indexOf(a) != -1 ? true : false;
    if(isFound)
      return b.substring(b.indexOf(a)+a.length() , b.length());

    else
      return "";

  }


  public static void main(String[] args)
  {

    testing();

  }

}
