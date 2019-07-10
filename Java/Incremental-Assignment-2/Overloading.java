public class Overloading
{
    Overloading()
    {
        this("This is not Default constructor");
        System.out.println("The Default constructor");
    }

    Overloading(String x)
    {
        this(5, 15);
        System.out.println(x);
    }

    Overloading(int x, int y)
    {
        System.out.println("This is number constructor " + x * y);
    }

    public static void main(String args[])
    {
        new Overloading();
    }
}
