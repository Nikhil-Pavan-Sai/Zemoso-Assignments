public class Part2
{

  public static int howMany(String a, String b)
  {

    int occurances = 0;
    while(b.contains(a))
    {
      b = b.replaceFirst(a, "");
      occurances++;
    }

    return occurances;

  }


  public static void testHowMany()
  {

     System.out.println(howMany("AA", "ATAAAA"));

  }

  public static void main(String[] args)
  {

    testHowMany();

  }

}
