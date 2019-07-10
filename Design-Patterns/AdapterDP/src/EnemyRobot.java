import java.util.Random;

public class EnemyRobot
{

	Random rand = new Random();
	
	public void smash() {
		// TODO Auto-generated method stub
		
		int damage = rand.nextInt(10) + 1;
		
		System.out.println("Does the damage of " + damage + " with hands");
		
	}

	public void walk() {
		// TODO Auto-generated method stub
		
		int motion = rand.nextInt(5) + 1;
		
		System.out.println("Moves " + motion);
		
	}

	public void react(String name) {
		// TODO Auto-generated method stub
		
		System.out.println("Enemy robot tramps on " + name);
		
	}
	
	
}