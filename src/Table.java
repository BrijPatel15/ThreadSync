import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Ingredient> ingredients = new ArrayList<>();
	private boolean isEmpty = true;
	private boolean readyToEat = false;

	public synchronized void putIngredients(List<Ingredient> ingredients) {
		while (!isEmpty) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		this.ingredients.addAll(ingredients);
		isEmpty = false;
		System.out.println(this.toString());
		notifyAll();
	}

	public synchronized List<Ingredient> getIngredients() {
		while (isEmpty && readyToEat) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Error " + e.getLocalizedMessage());
			}
		}
		this.readyToEat = true;
		List<Ingredient> tempIngredients = this.ingredients;
		notifyAll();
		return tempIngredients;
	}

	public synchronized void eat(Chef c) {
		while (isEmpty || !readyToEat) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isEmpty = true;
		readyToEat = false;
		System.out.println(c.toString() + " ate " + this.toString());
		this.ingredients.clear();
		notifyAll();
	}

	@Override
	public String toString() {
		return "Table has: " + ingredients.toString();
	}

}
