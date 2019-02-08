package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class JSONReader {

	Gson gson;
	
	public JSONReader(){
		
		gson = new Gson();
	}
	
	
	public <T> T getJSonObject(String filePath,Type type) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		return gson.fromJson(new FileReader("data/" + filePath), type);
	}
	
	
	public String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
	
	public JSONArray getJsonArrayFromString(String jsonContent,String key) 
	{
		return new JSONObject(jsonContent).getJSONArray(key);
	}
	
	
	public Object[][] getTabularJson(String fileName,String key) {
		
		Object[][] testData = null;
		
		try {
			String contents = readFileAsString("data/" + fileName);
			JSONArray options = getJsonArrayFromString(contents, key);
			
			int rows = options.length();
			int columns = ((JSONObject) options.get(0)).length();	
			
			System.out.println(rows + " rows");
			System.out.println(columns + " columns");
			
			testData = new Object[rows][columns];
			
			for (int row =0; row < rows; row++){
				
				testData[row][0] =  ((JSONObject) options.get(row)).get("browser");
				testData[row][1] =  ((JSONObject) options.get(row)).get("platform");
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return testData;
	}
	
	
	public Object[] getJsonArrayFromFile(String fileName,String key) 
	{
		String contents="";
		Object[] options;
		
		try {
			contents = readFileAsString("data/" + fileName);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		JSONArray jsonArray = getJsonArrayFromString(contents,key);
		options = new Object[jsonArray.length()];
		
		for (int option =0; option < options.length; option++){
			
			options[option] = jsonArray.get(option);
		}
		
		return options;
	}
	
		
	public static void main(String[] args) {
		
		JSONReader reader = new JSONReader();
		//This works if it has the returned json as array of objects
		//i.e , starts with '['
		
		/*try {
			List<Map> users = reader.getJSonObject("users.json", Object.class);
			System.out.println(users.size());			
			
			for (Map user:users) {
				System.out.println( user.get("firstname") );
				System.out.println( user.get("lastname") );
				System.out.println( user.get("password") );
				System.out.println( user.get("day") );
				System.out.println( user.get("month") );
				System.out.println( user.get("year") );
				System.out.println("-------------");
			}
			
		
		} catch (JsonSyntaxException e) {
			
			e.printStackTrace();			
		} catch (JsonIOException e) {
			
			e.printStackTrace();			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}*/
		 
		/* try {
			String contents = reader.readFileAsString("data/users.json");			
			System.out.println(contents);
			
			JSONArray jsonUsers = reader.getJsonArrayFromString(contents, "users");			
			
			for (Object user : jsonUsers)
			{
				System.out.println( ((JSONObject) user).get("firstname") );
				System.out.println( ((JSONObject) user).get("lastname") );
				System.out.println( ((JSONObject) user).get("password") );
				System.out.println( ((JSONObject) user).get("day") );
				System.out.println( ((JSONObject) user).get("month") );
				System.out.println( ((JSONObject) user).get("year") );
				System.out.println("-------------");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
		
	}
	
	
}