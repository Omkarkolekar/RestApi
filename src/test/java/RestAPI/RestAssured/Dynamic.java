	package RestAPI.RestAssured;
	
	import static io.restassured.RestAssured.given;
	
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAPI.DefaultMethod.ReUsableMethods;
import RestAPI.DefaultMethod.payload;
import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	
	public class Dynamic {
	
		public static String addBooknew(String isbnval, String aisleval) 
		{
			return "{\r\n" + 
					"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
					"\"isbn\":\""+isbnval+"\",\r\n" + 
					"\"aisle\":\""+aisleval+"\",\r\n" + 
					"\"author\":\"John foe\"\r\n" + 
					"}\r\n";
	
		}
	
		public static String rawJsonToString(String response, String responsekey)
		{
			JsonPath js =new JsonPath(response);
			return js.get(responsekey);
		}
	
	
			@Test(dataProvider="BooksData")
			public void addBook(String isbn,int aisle) {

			RestAssured.baseURI="http://216.10.245.166";
			String resp=given().
			header("Content-Type","/application/json").
			body(payload.addBooknew(isbn,aisle)).
			when().
			post("/Library/Addbook.php").
			then().assertThat().statusCode(200).
			extract().response().asString();
			JsonPath js= ReUsableMethods.rawToJson(resp);
			String id=js.get("ID");
			System.out.println(id);

			//deleteBOok

			}

			@DataProvider (name="BooksData")
			public Object[][] getData()
			{
				//array=collection of elements
				//multidimensional array= collection of arrays
				return new Object[][] {{"ojfwty",9363},{"cwetee","4253"}, {"okmfet","533"}};
				}

			
			
	/*
	 * public static void main(String args[]){
	 * 
	 * //declaring and initializing 2D array int arr[][]={{1,2,3},{2,4,5},{4,4,5}};
	 * 
	 * //printing 2D array for(int i=0;i<3;i++){ for(int j=0;j<3;j++){
	 * System.out.print(arr[i][j]+" "); } System.out.println(); }
	 * 
	 * }
	 */
			
	}
