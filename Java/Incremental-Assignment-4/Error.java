/*

Create three new types of exceptions. Write a class with a method that throws all three.
In main( ), call the method but only use a single catch clause that will catch
all three types of exceptions. Add a finally clause and verify that your finally
clause is executed, even if a NullPointerException is thrown.
*/

class Excep extends Exception{

  private String string;
  private int value;

  public Excep(String s)
  {
    super(s);
  }

  public Excep(String s, int val)
  {
    super(s + " "  + val);
  }

  public Excep()
  {
    super("Default");
  }
}

class Error{

  public static void main(String args[])
  {
    try{
        System.out.println("Execution Success");
        check(Integer.parseInt(args[0]));


      }
     catch(Exception e)
     {
       System.out.println(e.getMessage());
     }
     finally
     {
       System.out.println("Exiting Program!");
     }
  }

  public static void check(int z) throws Excep{
    if(z==0)  throw new Excep("This is a String Exception");
    if(z==1)  throw new Excep("This is a String and Integer Exception", 56);
    if(z==2)  throw new Excep();
  }
}
