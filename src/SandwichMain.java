
public class SandwichMain {

	public static void main(String[] args) {
		Table table = new Table();
		Agent agent = new Agent("Agent1", 20, 2, table);
		Chef c1 = new Chef(Ingredient.BREAD, table);
		Chef c2 = new Chef(Ingredient.JAM, table);
		Chef c3 = new Chef(Ingredient.PEANUTBUTTER, table);


		System.out.println("Program Started");
		Thread[] threadPool = new Thread[4];
		threadPool[0] = new Thread(agent);
		threadPool[1] = new Thread(c1);
		threadPool[2] = new Thread(c2);
		threadPool[3] = new Thread(c3);

		for (Thread t : threadPool) {
			t.start();
		}

	}

}
