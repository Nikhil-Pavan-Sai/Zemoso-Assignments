package SubwayHelpers;

public class Sauce extends SubwayDecorator
{



    public Sauce(Subway newSubway) {
        super(newSubway);
    }


    public String getDescr()
    {

        return subway.getDescr() + "Tomato SubwayHelpers.Sauce, Soya sauce, chilli sauce\n";
    }

    public double getCost()
    {

        return subway.getCost() + 2.00;
    }

}
