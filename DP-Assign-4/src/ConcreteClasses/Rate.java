package ConcreteClasses;

public class Rate implements CabInterface
{

    private String pickupLocation;
    private String dropLocation;


    public Rate(String pickup, String drop)
    {

        this.pickupLocation = pickup;
        this.dropLocation = drop;

    }


    @Override
    public String getCab(String cab) {
        return null;
    }

    @Override
    public String getCity(String city) {
        return null;
    }

    public String toString()
    {
        return Integer.toString(dropLocation.compareTo(pickupLocation) * 10);
    }

}
