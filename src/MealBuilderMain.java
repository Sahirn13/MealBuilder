import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.*;


public class MealBuilderMain {
	
	private final static int numberOfItems = 1500;
	public static int listNumber = 0;
	
	public static void main(String[] args) throws IOException {		
		
		
		FoodList allFoods = new FoodList();
		
		
		for(int i = 0; i < 4; i++) {
			int offSet = i*numberOfItems;
			allFoods.add(getAllFoods(offSet + 1));
		}
		
		
		results(allFoods, 100, 100, 100, 2000, 100);
		
	}
	
	public static void results(FoodList mainList, double targetPro, double targetFat, double targetCarbs, 
			double targetCals, double range) {
		
		FoodList temp = new FoodList();
		if(targetPro*4 + targetCarbs*4 + targetFat *9 > targetCals || targetPro == 0 || targetFat == 0 || targetCarbs == 0) {
			System.out.println("This combo of Calories and Macros is not possible, please try a different combo.");
		} else {
			results(mainList,temp, targetPro, targetFat,targetCarbs, targetCals, range, 0);
		}
		
		if(listNumber == 0) {
			System.out.println("No results were found!");
		} else {
			System.out.println(listNumber + " results were found");
		}
	}
	
	private static void results(FoodList mainList, FoodList temp, 
			double targetPro, double targetFat, double targetCarbs, 
			double targetCals, double range, int index) {
		
		if(listNumber < 10) {
			if((temp.getTotalCalories() <= targetCals + range 
					&& temp.getTotalCalories() >= targetCals - range)
					&& (temp.getTotalCarbs() <= targetCarbs + range/4 
					&& temp.getTotalCarbs() >= targetCarbs - range/4)
					&& (temp.getTotalFat() <= targetFat + range/9 
					&& temp.getTotalFat() >= targetFat - range/9) 
					&& (temp.getTotalProtein() <= targetPro + range/4
					&& temp.getTotalProtein() >= targetPro - range/4)) {
				listNumber++;
				System.out.println("Option number " + listNumber);
				System.out.println("------------------");
				System.out.println(temp.toString());
				System.out.println("Calories: " + temp.getTotalCalories());
				System.out.println("Carbs: " + temp.getTotalCarbs());
				System.out.println("Fat " + temp.getTotalFat());
				System.out.println("Protein " + temp.getTotalProtein());
				System.out.println("------------------");
				System.out.println(" ");
			} else {
				for(int i = index; i < mainList.size(); i++) {
					FoodItem item = mainList.getFoodItemAt(i);
					if(item.getCalories() != 0 && item.getCarbs() != 0 
							&& item.getFat() != 0 && item.getProtein() != 0 && temp.getCount(item) < 1) {
						if(temp.getTotalCarbs() < targetCarbs - range/4
								&& temp.getTotalFat() < targetFat - range/9
								&& temp.getTotalProtein() < targetPro - range/4
								&& temp.getTotalCarbs()*4 + temp.getTotalProtein()*4 + temp.getTotalFat()*9 < targetCals + range) {	
							temp.add(mainList.getFoodItemAt(i));
							results(mainList, temp, targetPro, targetFat, targetCarbs, targetCals, range, i);
							temp.remove();
						} 
					}
				}
			}
		}
	}
	
	
	
	@SuppressWarnings("unused")
	public static FoodList getAllFoods(int off) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		FoodList allFoods = new FoodList();
		
		File k = new File("secret.txt");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(k);
		String key = s.next();
			
		try{	
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			NameValuePair format = new BasicNameValuePair("format", "Json");
			NameValuePair api_key = new BasicNameValuePair("api_key", key);
			NameValuePair max = new BasicNameValuePair("max", "" + numberOfItems);
			NameValuePair offset = new BasicNameValuePair("offset", "" + off);
			NameValuePair protein = new BasicNameValuePair("nutrients", "203");
			NameValuePair fat = new BasicNameValuePair("nutrients", "204");
			NameValuePair carbs = new BasicNameValuePair("nutrients", "205");
			NameValuePair calories = new BasicNameValuePair("nutrients", "208");
			NameValuePair bakedGoods = new BasicNameValuePair("fg", "1800");
			NameValuePair beef = new BasicNameValuePair("fg", "1300");
			NameValuePair cereal = new BasicNameValuePair("fg", "0800");
			NameValuePair grains = new BasicNameValuePair("fg", "2000");
			NameValuePair dairy = new BasicNameValuePair("fg", "0100");
			NameValuePair fastFoods = new BasicNameValuePair("fg", "2100");
			NameValuePair fatsAndOils = new BasicNameValuePair("fg", "0400");
			NameValuePair fish = new BasicNameValuePair("fg", "1500");
			NameValuePair fruits = new BasicNameValuePair("fg", "0900");
			NameValuePair lamb = new BasicNameValuePair("fg", "1700");
			NameValuePair legumes = new BasicNameValuePair("fg", "1600");
			NameValuePair meals = new BasicNameValuePair("fg", "2200");
			NameValuePair nuts = new BasicNameValuePair("fg", "1200");
			NameValuePair pork = new BasicNameValuePair("fg", "1000");
			NameValuePair poultry = new BasicNameValuePair("fg", "0500");
			NameValuePair restaurant = new BasicNameValuePair("fg", "3600");
			NameValuePair luncheon = new BasicNameValuePair("fg", "0700");
			NameValuePair snacks = new BasicNameValuePair("fg", "1900");
			NameValuePair soups = new BasicNameValuePair("fg", "0600");
			NameValuePair vegies = new BasicNameValuePair("fg", "1100");
			
			params.add(format);
			params.add(api_key);
			params.add(max);
			params.add(offset);
			params.add(protein);
			params.add(fat);
			params.add(carbs);
			params.add(calories);
			params.add(bakedGoods);
			params.add(beef);
			params.add(cereal);
			params.add(grains);
			params.add(dairy);
			params.add(fastFoods);
			//params.add(fatsAndOils);
			params.add(fish);
			params.add(fruits);
			/*params.add(lamb);
			params.add(legumes);
			params.add(meals);
			params.add(nuts);
			params.add(pork);*/
			params.add(poultry);
			params.add(restaurant);
			/*params.add(luncheon);
			params.add(snacks);
			params.add(soups);
			params.add(vegies);*/
			
			
			URI uri = new URIBuilder() 
		        .setScheme("http")
		        .setHost("api.nal.usda.gov")
		        .setPath("/ndb/nutrients/")
		        .setParameters(params)
		        .build();
			
			HttpGet httpget = new HttpGet(uri);
			HttpResponse httpResponse = client.execute(httpget);
			HttpEntity entity = httpResponse.getEntity();
			
		    if (entity != null) {
		    	String result = EntityUtils.toString(entity);
		    	
		    	JSONObject Json = new JSONObject(result);
		    	Json = Json.getJSONObject("report");
		    	
		    	JSONObject item = new JSONObject();
		    	JSONArray nutrition = new JSONArray();
		    	
		    	for(int i = 0; i < Json.getJSONArray("foods").length(); i++) {
		    		item = Json.getJSONArray("foods").getJSONObject(i);
		    		nutrition = item.getJSONArray("nutrients");
		    		
		    		String name = item.get("name").toString();
		    		
		    		double protein2 = makeDouble(nutrition.getJSONObject(0).get("value").toString());
		    		double fat2 = makeDouble(nutrition.getJSONObject(1).get("value").toString());
		    		double carbs2 = makeDouble(nutrition.getJSONObject(2).get("value").toString());
		    		double calories2 = makeDouble(nutrition.getJSONObject(3).get("value").toString());

		    		FoodItem foodItem = new FoodItem(name, protein2, fat2, carbs2, calories2);
		    		
		    		allFoods.add(foodItem);
		    	}
		    }
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return allFoods;
	}
	
	
	//Looks at the String values from JSON data and converts nutrients to doubles
	public static double makeDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch(Exception e) {
			return 0.0;
		}
	}

}
