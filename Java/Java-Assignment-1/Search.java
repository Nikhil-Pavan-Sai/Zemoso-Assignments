/*

Create a java program to search through the home directory and look for files
that match a regular expression. The program should be able to take inputs repeatedly
and should print out the full absolute path of the matching files found.
*/


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
import java.util.*;
import java.io.*;


class RegexFileFilter implements FileFilter {

    private Pattern pattern;

    private Matcher matcher;

    * @param regex The regex pattern to check filenames against.
    */

		public RegexFileFilter(String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher("");
    }


    @Override
    public boolean accept(File file) {
        matcher.reset(file.getName());
        return matcher.matches();
    }
}

public class Search {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			File dir = new File(".");
			char ch;
			System.out.println("Do you want to search ? (y/n)");

			ch = (char)br.readLine().charAt(0);

			while(ch != 'n')
			{

				//RegexFileFilter takes the Regex as input and separates the files of that format.

				FileFilter fileFilter = new RegexFileFilter(br.readLine());


				File[] files = dir.listFiles(fileFilter); //Lists the files of the particular
				//format provided in the regex.

				for (int i = 0; i < files.length; i++)
				{
					System.out.println(files[i].getAbsolutePath()); //Provides the absolute path
					//of that file (full path of the file)

				}
				System.out.println("Do you want to search again ? (y/n)");

				ch = (char)br.readLine().charAt(0);
			}
		}
}
