public class AccountNumCheck
{

	private int accNum = 1234;
	
	public int getAccNum() { return accNum; }
	
	public boolean accActive(int acc)
	{
		
		if(acc == getAccNum())
		{
			
			return true;
		}
		
		else
		{
			return false;
		}
	}
}