import java.util.regex.Pattern;
/*
Question:
Using the documentation for java.util.regex.Pattern as a resource,
write and test a regular expression that checks a sentence to see that it begins with a capital letter
and ends with a period.
*/

class PatternExam
{

  static Pattern pattern = Pattern.compile("^[A-Z].*\\.$");
  static String strings[]= new String[]{
    "abcvjhb.",
    "Agfghh4843.",
    "BGDREHNDj BGB.",
    "Adfghj nytffgh !",
    "tb hjb jgwegh ?",
    "rtfgyneed knwjbu &",
    "Jvjh jghkfnr kuhjm kjmlm."
  };

  static boolean bool[] = {
    false,
    true,
    true,
    false,
    false,
    false,
    true
  };

  public static void main(String[] args) {
    checkPattern();
  }

  public static void checkPattern(){
    int i=1;

    for(String s : strings)
    {
      if(!pattern.matcher(s).matches())
        System.out.println("Doesn't match !" + i);
      i++;
    }

  }
}
