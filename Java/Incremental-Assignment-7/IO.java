/*
Using TextFile and a Map<Character,Integer>, create a program that takes the
file name as a command line argument and counts the occurrence of all the different characters.
Save the results in a text file.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

class IO
{
  public static void main(String[] args) throws IOException{

    Map<Character,Integer> map = new HashMap<>();
    File f=  new File(args[0]);
    BufferedReader br = new BufferedReader(new FileReader(f));

    FileWriter fw = new FileWriter("First.txt");

    int ch;
    while ( (ch = br.read()) >= 0)
    {
      int freq=1;
      if(map.containsKey((char)ch))
      {
        freq=map.get((char)ch)+1;
      }
      map.put((char)ch, freq);
    }

    for(Map.Entry<Character,Integer> entry : map.entrySet())
    {
      if(entry.getKey() != ' ')
        fw.write(entry.getKey() + " " + entry.getValue() + "\n");


      else
        fw.write("Space" + " " + entry.getValue() + "\n");
    }
    fw.close();

	}
}
