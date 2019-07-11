package FactoryClasses;

import ConcreteClasses.CabInterface;
import ConcreteClasses.City;

public class CityFactory implements AbstractCabFactory
{

    private String city;

    public CityFactory(String cityName)
    {

        this.city = cityName;

    }




    @Override
    public CabInterface requestCab() {
        return new City(city);
    }
}