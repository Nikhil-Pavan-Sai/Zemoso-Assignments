public class EnemyRobotAdapter implements EnemyAttacker
{
	
	EnemyRobot robot;
	
	public EnemyRobotAdapter(EnemyRobot enemyrobot)
	{
		
		robot = enemyrobot;
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
		robot.smash();
		
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		
		robot.walk();
		
	}

	@Override
	public void assign(String name) {
		// TODO Auto-generated method stub
		
		robot.react(name);
		
	}

	
}