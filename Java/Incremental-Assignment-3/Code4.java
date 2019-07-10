/*

Create a Cycle interface, with implementations Unicycle, Bicycle and Tricycle.
Create factories for each type of Cycle, and code that uses these factories.
*/

interface Cycle{

  void displayCycle();
}

class Unicycle implements Cycle{

  private String name;

  //Unicycle(){}

  Unicycle(String n)
  {
    name = n;
  }

  public void displayCycle(){

    System.out.println("The name of the Unicycle brand is " + name);
  }
}

class Bicycle implements Cycle{

  private String name;

  //Bicycle(){}

  Bicycle(String n)
  {
    name = n;
  }

  public void displayCycle(){

    System.out.println("The name of the Bicycle brand is " + name);
  }
}

class Tricycle implements Cycle{

  private String name;

  //Tricycle(){}

  Tricycle(String n)
  {
    name = n;
  }

  public void displayCycle(){

    System.out.println("The name of the Tricycle brand is " + name);
  }
}


class Factory
{
  public static Cycle getUniCycle(){ return new Unicycle("Atlas"); }
  public static Cycle getBiCycle(){ return new Bicycle("Hero"); }
  public static Cycle getTriCycle(){ return new Tricycle("Zemoso"); }

}

class Code4
{
  public static void main(String []args)
  {
    Cycle cycle1 = Factory.getUniCycle();
    Cycle cycle2 = Factory.getBiCycle();
    Cycle cycle3 = Factory.getTriCycle();


    cycle1.displayCycle();
    cycle2.displayCycle();
    cycle3.displayCycle();

  }
}
