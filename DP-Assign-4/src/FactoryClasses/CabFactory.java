package FactoryClasses;

import ConcreteClasses.Cab;
import ConcreteClasses.CabInterface;

public class CabFactory implements AbstractCabFactory
{


    private String cab;

    public CabFactory(String cabName)
    {

        this.cab = cabName;

    }


    @Override
    public CabInterface requestCab() {
        return new Cab(cab);
    }
}


