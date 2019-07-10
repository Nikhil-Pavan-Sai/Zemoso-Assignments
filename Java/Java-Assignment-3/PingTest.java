import java.io.BufferedReader;
import java.io.*;
import java.net.*;
import java.util.Arrays;


class PingTest
{

  static int MAX_PINGS = 10;
  static BufferedReader br;
  static double TimeEX[];

  public static void sendReq(String host)throws Exception
  {
    System.out.println("Working with ping");
    String str[] = new String[]{
      "ping",
      host,
      "-n",
      "-c",
      Integer.toString(MAX_PINGS),
    };
    ProcessBuilder pb = new ProcessBuilder(str);

    Process p = pb.start();

    br = new BufferedReader(new InputStreamReader(p.getInputStream()));
    br.readLine();

    getPingTimes();
  }

  public static void getPingTimes()throws Exception
  {
    TimeEX = new double[MAX_PINGS];
    for(int i=0;i<MAX_PINGS;i++)
    {
      String info[]=br.readLine().split(" ");
      TimeEX[i] = Double.parseDouble(info[6].substring(5));
    }
    Arrays.sort(TimeEX);
  }

  public static double medianTime()
  {
    int mid = MAX_PINGS/2;

    if(MAX_PINGS%2==0)
      return (TimeEX[mid]+TimeEX[mid+1])/2;
    else
      return TimeEX[mid];
  }


  public static void main(String args[])throws Exception
  {
    sendReq(args[0]);
    if(args.length==2)
      MAX_PINGS=Integer.parseInt(args[1]);
    System.out.println("The median time to ping host "+args[0]+" is "+medianTime());
  }
}
