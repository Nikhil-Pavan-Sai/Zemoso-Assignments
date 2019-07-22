public class BankFacade
{

	private int accNum;
	
	private int secNum;
	
	AccountNumCheck accCheck;
	
	SecurityCode secCheck;
	
	Funds funds;
	
	public BankFacade(int acc, int sec)
	{
		
		accNum = acc;
		
		secNum = sec;
		
		accCheck = new AccountNumCheck();
		
		secCheck = new SecurityCode();
		
		funds = new Funds();
	}
	
	public int getAccNum() { return accNum; }

	public int getSecNum() { return secNum; }
	
	public void withDraw(double cash)
	{
		if(accCheck.accActive(getAccNum()) && secCheck.isSecCode(getSecNum()) && funds.hasMoney(cash))
		{
			System.out.println("Transaction complete");
		}
		
		else
			System.out.println("Transaction failed");
	
	}
	
	public void depositCash(double cashToDeposit)
	{
		if(accCheck.accActive(getAccNum()) && secCheck.isSecCode(getSecNum()))
		{
			funds.deposit(cashToDeposit);
			
			System.out.println("Transaction complete");
		}
		
		else
			System.out.println("Transaction failed");
		
	}

	
}