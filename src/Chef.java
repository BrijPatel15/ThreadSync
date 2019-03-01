import java.util.List;

public class Chef implements Runnable {

	private Ingredient ingredient;
	private String name;
	private Table table;
	private long startTime;

	public Chef(Ingredient ingredient, Table table) {
		this.name = "Chef " + ingredient.toString();
		this.ingredient = ingredient;
		this.table = table;
	}

	@Override
	public void run() {
		while (true) {
			startTime = System.nanoTime();
			List<Ingredient> tableIngredients = table.getIngredients();
			if(tableIngredients != null && !tableIngredients.isEmpty()) {
				if (!tableIngredients.contains(this.ingredient)) {
					table.eat(this);
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

	}

	@Override
	public String toString() {
		return name;
	}

	public long startTime() {
		return this.startTime;
	}

}
