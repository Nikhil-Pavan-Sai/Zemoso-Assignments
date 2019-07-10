public class TomatoSauce extends ToppingDecorator
{

	public TomatoSauce(Pizza newPizza) {
		super(newPizza);
		// TODO Auto-generated constructor stub
		
		System.out.println("Add Tomato Sauce");
		
	}
	
	public String getDescr()
	{
		
		return pizza.getDescr() + ", Tomato Sauce";
	}
	
	public double getCost()
	{
		
		return pizza.getCost() + 5.00; 
	}

	
}