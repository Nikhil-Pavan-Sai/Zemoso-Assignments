public class Part1
{

  public String findSimpleGene(String DNA)
  {

    int startCodon = DNA.indexOf("ATG");
    int stopCodon = DNA.indexOf("TAA",startCodon+3);
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
                              "GJHVTAA",
                              "ATGUYUIOIJ",
                              "GHJKN",
                              "ATGJNHUKITAA",
                              "ATGHYJUBYAA"};


      for (String test_dna : dna_strings)
      {

        if(!findSimpleGene(test_dna).equals(""))
        { System.out.println("The Strings which follows the rule of string starting with ATG and ending with TAA " + findSimpleGene(test_dna)); }

      }
  }


  public static void main(String[] args)
  {

    Part1 sample = new Part1();
    sample.testSimpleGene();

  }



}
