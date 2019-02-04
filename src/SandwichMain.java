
public class SandwichMain {

	public static void main(String[] args) {
		Agent agent = new Agent("Agent1", 20, 2);
		Chef c1 = new Chef(Ingredient.BREAD);
		Chef c2 = new Chef(Ingredient.JAM);
		Chef c3 = new Chef(Ingredient.PEANUTBUTTER);
		
		System.out.println("Program Started");
		agent.run();
		c1.run();
		c2.run();
		c3.run();

	}

}
