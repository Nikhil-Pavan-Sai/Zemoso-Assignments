package SubwayHelpers;

public class Salad extends SubwayDecorator
{


    public Salad(Subway newSubway) {
        super(newSubway);
    }

    public String getDescr()
    {

        return subway.getDescr() + "Green SubwayHelpers.Salad along with vinegar\n";
    }

    public double getCost()
    {

        return subway.getCost() + 5.00;
    }

}
