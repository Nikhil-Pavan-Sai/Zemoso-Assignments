public class Adapter
{

	public static void main(String args[])
	{
		
		EnemyTank et = new EnemyTank();
		
		EnemyRobot er = new EnemyRobot();
		
		EnemyAttacker ea = new EnemyRobotAdapter(er);
		
		System.out.println("The robot: ");
		
		er.react("Jones");
		
		er.walk();
		
		er.smash();
		
		System.out.println("The enemy Tank: ");
		
		et.assign("Mua");
		
		et.drive();
		
		et.fire();
		
		System.out.println("The robot with adapter: ");
		
		ea.assign("Chan");
		
		ea.drive();
		
		ea.fire();
		
	}
}