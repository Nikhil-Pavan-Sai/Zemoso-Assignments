abstract class ToppingDecorator implements Pizza
{
	
	protected Pizza pizza;
	
	public ToppingDecorator(Pizza newPizza)
	{
		
		pizza = newPizza;
	}
	
	public String getDescr()
	{
		
		return pizza.getDescr();
	}
	
	public double getCost()
	{
		
		return pizza.getCost(); 
	}

}