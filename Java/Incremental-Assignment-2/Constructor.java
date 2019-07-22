/*Create a class with a constructor that takes a String argument. During construction,
print the argument. Create an array of object references to this class,
but don’t actually create objects to assign into the array. When you run the program,
notice whether the initialization messages from the constructor calls are printed.

Complete the previous exercise by creating objects to attach to the array of references.
*/


class Test{
  String str;

  Test(String s){
    str = s;

    System.out.println(str);
  }
}

public class Constructor
{
  public static void main(String[] args) {
      Test ls[] = new Test[10];

      for (int i=0; i<10; i++) {
        ls[i] = new Test("Hey !");
      }
  }
}
