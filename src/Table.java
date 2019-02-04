import java.util.List;

public class Table {
	private List<Ingredient> ingredients;
	private boolean isPutting = false;
	private boolean isTaking = false;
	private boolean canTake = false;
	public Table() {
		super();
	}
	
	public synchronized void putIngredients(List<Ingredient> aInIngredients) {
		while(isPutting) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		isPutting = true;
		if(!ingredients.isEmpty()) {
			ingredients.clear();
		}
		ingredients.addAll(aInIngredients);
		isPutting = false;
		canTake = true;
		notifyAll();
	}
	
	public synchronized List<Ingredient> getIngredients(){
		while(isTaking || !canTake) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		
		isTaking = true;
		if(ingredients.isEmpty() || ingredients == null) {
			canTake = false;
			isTaking = false;
			notifyAll();
			return null;
		} else {
			isTaking = false;
			notifyAll();
			return ingredients;
		}
	}

}
