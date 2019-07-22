public class SecurityCode
{

	
	private int secNum = 0210;
	
	public int getSecNum() { return secNum; }
	
	public boolean isSecCode(int acc)
	{
		
		if(acc == getSecNum())
		{
			
			return true;
		}
		
		else
		{
			return false;
		}
	}
}