public class Mozz extends ToppingDecorator
{

	public Mozz(Pizza newPizza) {
		super(newPizza);
		// TODO Auto-generated constructor stub
		
		System.out.println("Add Dough");
		
		System.out.println("Add Mozz");
	}
	
	public String getDescr()
	{
		
		return pizza.getDescr() + ", Mozzerella";
	}
	
	public double getCost()
	{
		
		return pizza.getCost() + 10.00; 
	}

	
}