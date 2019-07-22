/* Question
Create a Cycle class, with subclasses Unicycle, Bicycle and Tricycle.
Add a balance( ) method to Unicycle and Bicycle, but not to Tricycle.
Create instances of all three types and upcast them to an array of Cycle.
Try to call balance( ) on each element of the array and observe the results.
Downcast and call balance( ) and observe what happens.
*/


class CycleMain{
  public static void main(String[] args) {

    Cycle cy[] = new Cycle[3];

    cy[0] = new Unicycle();
    cy[1] = new Bicycle();
    cy[2] = new Tricycle();


    //This code won't work because the base class (Cycle) doesn't know the balance class which is declared inside the sub-classes.
    //The cy[2].balance() also won't work because it is not declared inside the Tricycle class
    /*for (int i=0; i<3; i++) {
      cy[i].balance();
    }*/

    ((Unicycle)cy[0]).balance();
    ((Bicycle)cy[1]).balance();

  }
}

class Cycle{

  String brand;

  void setBrand(String name) { brand = name; }
  String getBrand() { return brand; }
}

class Unicycle extends Cycle{
  Unicycle(){
    setBrand("Hero");
    System.out.println("This is Unicycle of " + getBrand() + " brand.");
  }

  public void balance(){
    System.out.println("This has not much balance");
  }
}

class Bicycle extends Cycle{
  Bicycle(){
    setBrand("Zemoso");
    System.out.println("This is Bicycle of " + getBrand() + " brand.");
  }

  public void balance(){
    System.out.println("This has pretty much balance");
  }
}

class Tricycle extends Cycle{
  Tricycle(){
    setBrand("Atlas");
    System.out.println("This is Tricycle of " + getBrand() + " brand.");
  }
}
