package Test_Main;

import ConcreteClasses.CabInterface;
import FactoryClasses.AbstractCabFactory;

public class ImplementFactory
{

    public static CabInterface getRequestedCab(AbstractCabFactory acb)
    {

        return acb.requestCab();

    }

}
