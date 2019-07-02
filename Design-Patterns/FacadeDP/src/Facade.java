public class Facade
{

	public static void main(String args[])
	{
		
		BankFacade access = new BankFacade(1234, 0210);
		
		access.withDraw(50.00);
		
		access.withDraw(400.00);
		
		access.depositCash(200.00);
		
	}
}