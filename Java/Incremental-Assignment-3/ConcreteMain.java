/* Question

Create three interfaces, each with two methods. Inherit a new interface that
combines the three, adding a new method. Create a class by implementing the new interface
and also inheriting from a concrete class. Now write four methods, each of which takes
one of the four interfaces as an argument. In main( ), create an object of your class
and pass it to each of the methods.
*/


interface Monitor
{
  void DisplayMon();
  void monMacAddr(String m);

}

interface Laptop extends Monitor
{
  void DisplayLap();
  void lapMacAddr(String m);

}

interface Mobile extends Laptop
{
  void DisplayMob();
  void mobMacAddr(String m);

}

interface Tablet extends Mobile
{
  void getUseOf();

}



class ConcreteMain{

  public static void main(String[] args) {

    SimpleClass simple = new SimpleClass();

    MonDisp(simple);
    LapDisp(simple);
    MobDisp(simple);
    TabDisp(simple);

  }

  public static void MonDisp(Monitor myMon)
  {

    myMon.DisplayMon();
    myMon.monMacAddr("ba:0a:14:12:68:16");
  }

  public static void LapDisp(Laptop myLap)
  {

    myLap.DisplayLap();
    myLap.lapMacAddr("7a:0a:84:12:68:56");
  }

  public static void MobDisp(Mobile myMob)
  {

    myMob.DisplayMob();
    myMob.mobMacAddr("88:aa:84:00:68:00");
  }

  public static void TabDisp(Tablet myTab)
  {
    myTab.getUseOf();
  }

}


class ConcreteClass{

  private String mac;

  void printMac(String m) {
    mac = m;
    System.out.println("The sample format of MAC address: " + mac);
  }
}

class SimpleClass extends ConcreteClass implements Tablet{

  SimpleClass(){
    printMac("00:0a:95:9d:68:16");
  }

  public void DisplayMon(){
    System.out.println("This is monitor.");
  }

  public void monMacAddr(String m){
    System.out.println("Monitor's MAC is: " + m);
  }

  public void DisplayLap(){
    System.out.println("This is laptop.");
  }

  public void lapMacAddr(String m){
    System.out.println("Laptop's MAC is: " + m);
  }

  public void DisplayMob(){
    System.out.println("This is mobile.");
  }

  public void mobMacAddr(String m){
    System.out.println("Mobile's MAC is: " + m);
  }

  public void getUseOf(){
    System.out.println("Just for implementation");
  }

}
