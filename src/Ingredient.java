import java.util.Random;

public enum Ingredient {
	
	BREAD("Bread"), PEANUTBUTTER("Peanut Butter"), JAM("Jam");
	
	private final String name;
	
	private Ingredient(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static Ingredient getRandom() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
	
}
