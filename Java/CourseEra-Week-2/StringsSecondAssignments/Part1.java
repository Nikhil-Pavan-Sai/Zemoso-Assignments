public class Part1
{

  public static int findStopCodon(String dna, int startIndex, String stopCodon)
  {

    int stopIndex = dna.indexOf(stopCodon, startIndex+3);

    return stopIndex == -1 || (stopIndex-startIndex) % 3 != 0 ? dna.length() : stopIndex;

  }

  public static void testFindStopCodon()
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
    for(int i=0; i<test_cases.length; i++)
    {
      if(test_cases[i].indexOf("ATG") != -1)
      {
        int stop = findStopCodon(test_cases[i], test_cases[i].indexOf("ATG"), "TAA");
        if(stop != test_cases[i].length())
          System.out.println(test_cases[i] + "---" +test_cases[i].substring(test_cases[i].indexOf("ATG")+3, stop).length());

        else
          System.out.println(test_cases[i] + "---" + test_cases[i].length());


      }
      else
        System.out.println(test_cases[i] + "---" + test_cases[i].length());
    }
  }


  public static String findGene(String dna)
  {

    int startIndex = dna.indexOf("ATG");
    if(startIndex == -1)
      return "";


    int stopIndex = findStopCodon(dna, startIndex, "TAA");
    int stopIndex1 = findStopCodon(dna, startIndex, "TAG");
    int stopIndex2 = findStopCodon(dna, startIndex, "TGA");

    int min = Math.min(Math.min(stopIndex, stopIndex1), stopIndex2);

    if(min != dna.length())
      return dna.substring(startIndex+3, min);

    else
      return "";

  }

  public static void testFindGene()
  {

    String[] test = {"ATGTAA",
      "ATGGTATAG",
      "AAATGCCCTGACTAGATTAAGAAACC",
      "AGTCAA",
      "AGTCCGTAGATCGAC",
      "AATGCTAACTAGCTGACTAAT"};

      for (String str : test)
      {

        if(findGene(str) != "")
          System.out.println(findGene(str));

        else
          System.out.println("There is no substring multiple of 3");


      }

      for (String str : test)
      {

        printAllGenes(str);

      }

  }


  public static void printGene(String dna,int startIdx,int stopIdx)
  {
    if(stopIdx!=dna.length())
      System.out.println("Gene found is: "+ (dna.substring(startIdx+3,stopIdx).length() > 0 ? dna.substring(startIdx+3,stopIdx) : "There is no substring multiple of 3"));
  }



  public static void printAllGenes(String dna)
  {
    int startIdx = dna.indexOf("ATG");

    while(startIdx!=-1)
    {
      printGene(dna, startIdx, findStopCodon(dna, startIdx, "TAA"));
      printGene(dna,startIdx,findStopCodon(dna, startIdx, "TGA"));
      printGene(dna,startIdx,findStopCodon(dna, startIdx, "TAG"));
      startIdx = dna.indexOf("ATG",startIdx+3);
    }
  }



  public static void main(String[] args)
  {

    testFindStopCodon();
    testFindGene();

  }


}
