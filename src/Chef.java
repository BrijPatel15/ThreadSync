import java.util.List;

public class Chef implements Runnable {

	private Ingredient ingredient;
	private String name;
	private Table table;
	
	public Chef(Ingredient ingredient, Table table) {
		this.name = "Chef " + ingredient.toString();
		this.ingredient = ingredient;
		this.table = table;
	}
	
	@Override
	public void run() {
		while(true) {
			List<Ingredient> tableIngredients = table.getIngredients();
			System.out.println(this.toString());
			if(tableIngredients != null && !tableIngredients.isEmpty()) {
				if (tableIngredients.contains(ingredient)) {
					System.out.println(this.toString() + " picked up: " + tableIngredients.toString());
					table.putIngredients(tableIngredients);
					System.out.println(this.toString() + " put back: " + tableIngredients.toString());
				} else {
					System.out.println(this.toString() + " ate: " + tableIngredients.toString());
					table.eatIngredients();
				}
			}
		}

	}
	
	@Override
	public String toString() {
		return name;
	}

}
