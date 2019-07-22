public class Decorator
{

	
	public static void main(String args[])
	{
		
		Pizza pizza = new TomatoSauce(new Mozz(new PlainPizza())); 
		
		System.out.println("Add-ons: " + pizza.getDescr());
		
		System.out.println("Cost: " + pizza.getCost());
	}
}
