package FactoryClasses;

import ConcreteClasses.CabInterface;
import ConcreteClasses.Rate;

public class RateFactory implements AbstractCabFactory {


    private String pickup;
    private String drop;

    public RateFactory(String pickupLoc, String dropLoc)
    {

        this.pickup = pickupLoc;
        this.drop = dropLoc;

    }

    @Override
    public CabInterface requestCab() {
        return new Rate(pickup, drop);
    }

}
