
public class Agent implements Runnable {
	
	private String name;
	private int numSandwiches;
	private int numIngredients;
	
	public Agent(String name, int numSandwiches, int numIngredients) {
		this.name = name;
		this.numIngredients = numIngredients;
		this.numSandwiches = numSandwiches;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		return "Agent " + name;
	}

}
