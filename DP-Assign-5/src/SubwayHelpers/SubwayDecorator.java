package SubwayHelpers;

public class SubwayDecorator implements Subway
{

    protected Subway subway;

    SubwayDecorator(Subway newSubway)
    {

        subway = newSubway;
    }


    @Override
    public String getDescr() {
        return subway.getDescr();
    }

    @Override
    public double getCost() {
        return subway.getCost();
    }
}
