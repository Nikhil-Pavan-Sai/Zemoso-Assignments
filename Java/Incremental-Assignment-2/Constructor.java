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
