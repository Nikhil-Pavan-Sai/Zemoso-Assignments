import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


public class SyncDoc
{

    public static void main(String args[]) throws IOException
    {

        HashMap<String, String> lis = new HashMap<>();

        List<String> list = new ArrayList<>();

        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");

        Random rand = new Random();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DocAdapter da = new DocAdapter();

        System.out.println("We deliver best service. Our Specialities are: ");

        System.out.println("1. Physiology" + "\n" + "2. Neurology" + "\n" + "3. Dermatology" + "\n" + "4. Cardiology" + "\n" + "5. Gastroenterology" + "\n" + "6. Nephrology");

        System.out.println("Select the area of treatment: ");

        da.addDoc();

        int choice = Integer.parseInt(br.readLine());

        lis = da.displayDoc(choice);

        for (String key:lis.keySet())
        {
            System.out.println(key);
        }

        System.out.println("choose the desired doctor name: ");

        String name = br.readLine();

        System.out.println("The available time of the desired doctor is: " + lis.get(name));

        System.out.println("Choose the desired time(Enter in Hour <Space> Minutes) in between the available time of the Doctor: ");

        int time[] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Your booking is Confirmed ! \n Your Time slot is: " + time[0] + ":" + time[1] + " on " + list.get(rand.nextInt(list.size())));




    }
}