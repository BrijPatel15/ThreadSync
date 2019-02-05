import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Ingredient> ingredients = new ArrayList<>();
	private boolean isPutting = false;
	private boolean isTaking = false;
	private boolean hasChefEaten = true;
	private final int MAX_SIZE = 2;
	public Table() {
		super();
	}
	
	public synchronized void putIngredients(List<Ingredient> aInIngredients) {
		while(isPutting || !hasChefEaten) { //only put when someone is not putting or the table is empty
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		isPutting = true;
		hasChefEaten = false;
		if (ingredients.size() < MAX_SIZE) {
			ingredients.addAll(aInIngredients);
		}
		isPutting = false;
		notifyAll();
	}
	
	public synchronized List<Ingredient> getIngredients(){
		while(isTaking || ingredients.isEmpty()) {// can only if someone is not taking and the table is "full"
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		
		isTaking = false;
		notifyAll();
		
		return ingredients;
	}
	
	public synchronized void eatIngredients() {
		while((isTaking || isPutting || hasChefEaten) && ingredients.isEmpty()) {// When someone is not taking or putting and table is "full"
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
			isTaking = true;
			isPutting = true;
			hasChefEaten = true;
			ingredients.clear();
			isTaking = false;
			isPutting = false;
			notifyAll();
		}
	}
	
	@Override
	public String toString() {
		return "Table has: " + ingredients.toString();
	}

}
