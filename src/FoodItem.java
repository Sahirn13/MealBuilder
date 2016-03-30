
public class FoodItem {
	private String name;
	private double protein;
	private double fat;
	private double carbs;
	private double calories;
	
	public FoodItem(String name, double protein, double fat, 
			double carbs, double calories) {
		
		this.name = name;
		this.protein = protein;
		this.fat = fat;
		this.carbs = carbs;
		this.calories = calories;
		
	}
	
	public String getName() {
		return name;
	}
	
	public double getProtein() {
		return protein;
	}
	
	public double getFat() {
		return fat;
	}
	
	public double getCarbs() {
		return carbs;
	}
	
	public double getCalories() {
		return calories;
	}
	
	public String toString() {
		return 
		//"----------------------------" 
		"\n" + name + "\n" + 
		"Protein = " + protein + "\n" +
		"Fat = " + fat + "\n" +
		"Carbs = " + carbs + "\n" +
		"Calories = " + calories + "\n";
		//"----------------------------";
	}
}
