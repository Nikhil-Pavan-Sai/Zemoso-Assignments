import SubwayHelpers.*;

/*
*
* Consider the famous restaurant chain Subway. Write a program to create a Subway sub based on the user specifications
* and calculate the cost accordingly.
*
*
* Classes : Bread, Salad, Sauce, Addons
* */



public class Solution
{

    public static void main(String[] args)
    {

        Subway subway = new Addons(new Sauce(new Salad(new Bread())));


        System.out.println("Ingredients in the SubwayHelpers.Subway are: " + subway.getDescr());


        System.out.println("Cost for the SubwayHelpers.Subway is: " + subway.getCost());
    }

}
