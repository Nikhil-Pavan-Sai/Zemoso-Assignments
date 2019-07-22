public class Part3
{

  public static int findStopCodon(String dna, int startIndex, String stopCodon)
  {

    int stopIndex = dna.indexOf(stopCodon, startIndex+3);

    return stopIndex == -1 || (stopIndex-startIndex) % 3 != 0 ? dna.length() : stopIndex;

  }


  public static int countGenes(String dna)
  {

    int startIndex = dna.indexOf("ATG");
    int result = 0;

    while( startIndex != -1)
    {

      if(findStopCodon(dna, startIndex, "TAA") != dna.length())
        result++;

      if(findStopCodon(dna, startIndex, "TAG") != dna.length())
        result++;

      if(findStopCodon(dna, startIndex, "TGA") != dna.length())
          result++;

      startIndex = dna.indexOf("ATG", startIndex+3);

    }

    return result;


  }


  public static void testCountGenes()
  {

    String[] test_cases = {"ATGVUION",
    "AGJMVHK",
    "ATGHJFYBITAA",
    "TYGUHKJTAA",
    "ATGTAA",
    "HGGHUHUBKJB",
    "ATGKKLKNBGUKLN",
    "ATGHJINJKTAA",
    "ATGGBYHTTAA"};


    for(String str : test_cases)
      System.out.println(countGenes(str));

  }



  public static void main(String[] args)
  {

    testCountGenes();

  }


}
