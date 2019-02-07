import java.util.ArrayList;
import java.util.List;


public class Agent implements Runnable {

	private String name;
	private int numSandwiches;
	private int numIngredients;
	private Table table;

	public Agent(String name, int numSandwiches, int numIngredients, Table table) {
		this.name = name;
		this.numIngredients = numIngredients;
		this.numSandwiches = numSandwiches;
		this.table = table;
	}

	@Override
	public void run() {
		for(int i = 1; i <= numSandwiches; i++) {
			List<Ingredient> tempList = getRandIngredients();
			System.out.println(this.toString() + " Sandwich count: " + i + ": Putting: " + tempList.toString());
			table.putIngredients(tempList);
			try {
				Thread.sleep(500);// Made to make the output more readable
			} catch (InterruptedException e) {
				System.out.println("Agent sleep error " + e.getLocalizedMessage());
			}
		}
		while (table.getIngredients().isEmpty()) {
			System.out.println(numSandwiches + " sandwiches made Agent is done.");
			System.exit(0);
		}
	}

	private List<Ingredient> getRandIngredients(){
		List<Ingredient> $finalList = new ArrayList<>();
		for(int i = 1; i <= numIngredients; i++) {
			Ingredient temp = Ingredient.getRandom();
			if(!$finalList.contains(temp)) {
				$finalList.add(temp);
			} else {
				i = i -1;
			}
		}
		return $finalList;
	}

	@Override
	public String toString() {
		return "Agent " + name;
	}

}
