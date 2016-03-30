import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodList {
	private ArrayList<FoodItem> list;
	private double totalProtein;
	private double totalFat;
	private double totalCarbs;
	private double totalCalories;
	private Map<FoodItem, Integer> count;
	
	public FoodList() {
		this.list = new ArrayList<FoodItem>();
		this.totalProtein = 0;
		this.totalFat = 0;
		this.totalCarbs = 0;
		this.totalCalories = 0;
		this.count = new HashMap<FoodItem, Integer>();
	}
	
	public FoodList(FoodItem item) {
		list.add(item);
		this.totalProtein = item.getProtein();
		this.totalFat = item.getFat();
		this.totalCarbs = item.getCarbs();
		this.totalCalories = item.getCalories();
		this.count.put(item, 1);
	}
	
	public FoodList(ArrayList<FoodItem> list) {
		this.list = list;
		for(int i = 0; i < list.size(); i++) {
			this.totalProtein += list.get(i).getProtein();
			this.totalFat += list.get(i).getFat();
			this.totalCarbs += list.get(i).getCarbs();
			this.totalCalories += list.get(i).getCalories();
			if(!this.count.containsKey(list.get(i))) {
				this.count.put(list.get(i), 1);
			} else {
				this.count.put(list.get(i), this.count.get(list.get(i)) + 1);
			}
		}
	}
	
	public FoodItem getFoodItemAt(int i) {
		return this.list.get(i);
	}
	
	public double getTotalProtein() {
		return totalProtein;
	}
	
	public double getTotalFat() {
		return totalFat;
	}
	
	public double getTotalCarbs() {
		return totalCarbs;
	}
	
	public double getTotalCalories() {
		return totalCalories;
	}
	
	public void add(FoodItem item) {
		this.list.add(item);
		this.totalProtein += item.getProtein();
		this.totalFat += item.getFat();
		this.totalCarbs += item.getCarbs();
		this.totalCalories += item.getCalories();
		if(!this.count.containsKey(item)) {
			this.count.put(item, 1);
		} else {
			this.count.put(item, this.count.get(item) + 1);
		}
		
	}
	
	public void add(FoodList l) {
		this.totalProtein += l.getTotalProtein();
		this.totalFat += l.getTotalFat();
		this.totalCarbs += l.getTotalCarbs();
		this.totalCalories += l.getTotalCalories();
		for(int i = 0; i < l.size(); i++) {
			if(!this.count.containsKey(l.getFoodItemAt(i))) {
				this.count.put(l.getFoodItemAt(i), 1);
			} else {
				this.count.put(l.getFoodItemAt(i), this.count.get(l.getFoodItemAt(i)) + 1);
			}
			this.list.add(l.getFoodItemAt(i));
		}
	}
	
	public void remove() {
		if(!list.isEmpty()) {
			this.removeAt(this.list.size() - 1);
		}
	}
	
	public void removeAt(int i) {
		this.totalProtein -= list.get(i).getProtein();
		this.totalFat -= list.get(i).getFat();
		this.totalCarbs -= list.get(i).getCarbs();
		this.totalCalories -= list.get(i).getCalories();
		this.count.put(this.list.get(i), this.count.get(this.list.get(i)) - 1);
		this.list.remove(i);
	}
	
	public int size() {
		return list.size();
	}
	
	public int getCount(FoodItem i) {
		if(this.count.containsKey(i)) {
			return this.count.get(i);
		} else {
			return 0;
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < list.size(); i++) {
			s += list.get(i).toString() + "\n";
		}
		return s;
	}
}
