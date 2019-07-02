public class Funds
{

	private double amount = 1000.00;
	
	public double getCash() { return amount; }
	
	public void decreaseCash(double cash)
	{
		
		amount -= cash;
	}
	
	public void depositCash(double cash)
	{
		
		amount += cash;
		
	}
	
	public boolean hasMoney(double withdraw)
	{
		
		if(withdraw > amount)
		{
			System.out.println("You don't have enough funds" + "\n" + "Balance: " + getCash());
			return false;
		}
		
		
		else
		{
			decreaseCash(withdraw);
			System.out.println("Balance is: " + getCash());
			return true;
		}
	}
	
	public void deposit(double amt)
	{
		
		depositCash(amt);
		
		System.out.println("Deposit transaction complete" + "\n" + "Balance: " + getCash());

		
		
	}
	
}