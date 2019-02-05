
public class SandwichMain {

	public static void main(String[] args) {
		Table table = new Table();
		Agent agent = new Agent("Agent1", 20, 2, table);
		Chef c1 = new Chef(Ingredient.BREAD, table);
		Chef c2 = new Chef(Ingredient.JAM, table);
		Chef c3 = new Chef(Ingredient.PEANUTBUTTER, table);
		
		System.out.println("Program Started");
		agent.run();
		c1.run();
		c2.run();
		c3.run();

	}

}
