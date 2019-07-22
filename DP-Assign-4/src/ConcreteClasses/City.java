package ConcreteClasses;

public class City implements CabInterface
{


    private String[] cities = {"Hyderabad", "Chennai", "Bengaluru"};

    private String CITY;


    public City(String city)
    {

        this.CITY = city;

    }


    @Override
    public String getCab(String cab) {
        return null;
    }

    @Override
    public String getCity(String cityName) {


        for (String s : cities) {
            if (s.equals(cityName))
                return cityName;
        }

        return "Not in Range !";
    }

    public String toString()
    {
        return CITY;
    }
}
