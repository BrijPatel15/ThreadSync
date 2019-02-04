
public class Chef implements Runnable {

	private Ingredient ingredient;
	private String name;
	
	public Chef(Ingredient ingredient) {
		this.name = "Chef " + ingredient.toString();
		this.ingredient = ingredient;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString() {
		return name;
	}

}
