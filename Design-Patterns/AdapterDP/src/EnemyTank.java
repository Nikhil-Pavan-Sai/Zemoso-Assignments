import java.util.Random;

public class EnemyTank implements EnemyAttacker
{

	Random rand = new Random();
	
	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
		int damage = rand.nextInt(10) + 1;
		
		System.out.println("Does the damage of " + damage);
		
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		
		int motion = rand.nextInt(5) + 1;
		
		System.out.println("Moves " + motion);
		
	}

	@Override
	public void assign(String name) {
		// TODO Auto-generated method stub
		
		System.out.println(name + " is driving the Tank");
		
	}
	
	
}