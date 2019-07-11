package SubwayHelpers;

public class Addons extends SubwayDecorator
{


    public Addons(Subway newSubway) {
        super(newSubway);
    }

    public String getDescr()
    {

        return subway.getDescr() + "Added extra addons like: Lettuce, Broccoli, Mayo etc.\n";
    }

    public double getCost()
    {

        return subway.getCost() + 10.00;
    }

}
