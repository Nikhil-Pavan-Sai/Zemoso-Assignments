/* Question

Create an inheritance hierarchy of Rodent: Mouse, Gerbil, Hamster,etc.
In the base class, provide methods that are common to all Rodents, and override
these in the derived classes to perform different behaviors depending on the specific type of Rodent.
Create an array of Rodent, fill it with different specific types of Rodents, and call your base-class methods
to see what happens. Make the methods of Rodent abstract whenever possible and all classes should have
default constructors that print a message about that class.
*/


class AccessControl{

  public static void main(String[] args) {
    Rodent rod[] = new Rodent[3];
    rod[0] = new Mouse();
    rod[1] = new Gerbil();
    rod[2] = new Hamster();

    for (int i=0; i<3; i++) {
      rod[i].eating();
    }

  }
}

abstract class Rodent{
  private String name;
  private int age;

  Rodent(){
    System.out.println("This is a Rodent");
  }

  void setName(String n) { name = n; }
  String getName() { return name; }

  void setAge(int a) { age = a; }
  int getAge() { return age; }

  abstract void eating();

}

class Mouse extends Rodent{

  Mouse()
  {
    super();
    setName("Stuart");
    System.out.println("Hey ! I am "+ getName() + ".");
    setAge(12);
    System.out.println("I am "+ getAge() + " years old.");
  }

  void eating(){
    System.out.println(getName() + "- " + "I eat cheese !");
  }
}

class Gerbil extends Rodent{

  Gerbil()
  {
    super();
    setName("Phineas");
    System.out.println("Hey ! I am "+ getName() + ".");
    setAge(14);
    System.out.println("I am "+ getAge() + " years old.");
  }

  void eating(){
    System.out.println(getName() + "- " + "I eat you ! Hahaha !");
  }
}

class Hamster extends Rodent{

  Hamster()
  {
    super();
    setName("Cary");
    System.out.println("Hey ! I am "+ getName() + ".");
    setAge(8);
    System.out.println("I am "+ getAge() + " years old.");
  }

  void eating(){
    System.out.println(getName() + "- " + "I eat worms ! Burgers are made of me !");
  }
}
