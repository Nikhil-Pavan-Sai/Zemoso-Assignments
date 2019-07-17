public class Part2
{

  public String findSimpleGene(String DNA, String start, String stop)
  {

    if(DNA.charAt(0)>='a' && DNA.charAt(0)<='z'){
      start = start.toLowerCase();
      stop = stop.toLowerCase();
    }
    else
    {
      start = start.toUpperCase();
      stop = stop.toUpperCase();
    }



    int startCodon = DNA.indexOf(start);
    int stopCodon = DNA.indexOf(stop ,startCodon+3);
    if(startCodon!=-1 && stopCodon!=-1)
    {
       String subs = DNA.substring(startCodon+3, stopCodon);
       if ( subs.length() % 3 == 0 )
       {
         return subs;
       }
    }
    return "";

  }

  public void testSimpleGene()
  {


      String[] dna_strings = {"FVGTH",
                              "CVGTRH",
                              "atggctccataa",
                              "GJHVTAA",
                              "ATGUYUIOIJ",
                              "GHJKN",
                              "atgtagattcggctctgjtaa",
                              "atggctccataa",
                              "ATGJNHUKITAA",
                              "ATGHYJUBYAA"};


      for (String test_dna : dna_strings)
      {
        if(!findSimpleGene(test_dna, "atg", "taa").equals(""))
        { System.out.println("The Strings which follows the rule of string starting with ATG and ending with TAA " + findSimpleGene(test_dna, "atg", "taa")); }

      }
  }


  public static void main(String[] args)
  {

    Part2 sample = new Part2();
    sample.testSimpleGene();

  }


}
