package ConcreteClasses;

public class Cab implements CabInterface
{

    private String[] cabs = {"Sedan" , "SUV", "Micro", "Mini"};

    private String CAB;



    public Cab(String cab)
    {

        this.CAB = cab;

    }



    @Override
    public String getCab(String cabName) {

        for (String s : cabs) {
            if (s.equals(cabName))
                return cabName;
        }

        return "Not in Range !";
    }

    @Override
    public String getCity(String cityName) {
        return null;
    }

    public String toString()
    {
        return CAB;
    }
}
