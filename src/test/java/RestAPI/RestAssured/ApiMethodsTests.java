package RestAPI.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import javafx.beans.binding.When;

public class ApiMethodsTests {
	
	public static String addPlace() 
	{
		return "{\r\n" + 
        		"  \"location\": {\r\n" + 
        		"    \"lat\": -38.383494,\r\n" + 
        		"    \"lng\": 33.427362\r\n" + 
        		"  },\r\n" + 
        		"  \"accuracy\": 50,\r\n" + 
        		"  \"name\": \"Frontline house\",\r\n" + 
        		"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
        		"  \"address\": \"26565, side layout, cohen 09\",\r\n" + 
        		"  \"types\": [\r\n" + 
        		"    \"shoe park\",\r\n" + 
        		"    \"shop\"\r\n" + 
        		"  ],\r\n" + 
        		"  \"website\": \"http://google.com\",\r\n" + 
        		"  \"language\": \"French-IN\"\r\n" + 
        		"}";
		
	}
	
	public static String rawJsonToString(String response, String responsekey)
	{
		JsonPath js1 =new JsonPath(response);
		return js1.getString(responsekey);
	}
	
	
	@Test
    void getUserDetailsTest() {
        //The base URI to be used
        RestAssured.baseURI = "https://rahulshettyacademy.com";
 
        ////post
		String response = given()/* .log().all() */.queryParam("key","qaclick123").header("Content-Type", "application/json")
        .body(addPlace()).when().post("maps/api/place/add/json")
				.then()/* .log().all() */.assertThat().statusCode(200).body("scope",equalTo("APP"))
        .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
        
        System.out.println(response);
      //  JsonPath js = new JsonPath(response);
        
      //  String placeId = rawJsonToString(response,"place_id");
        System.out.println(rawJsonToString(response,"place_id"));
        String newAddress = "Summer Walk, Africa";
		
        ////put
        given().log().all() .queryParam("key","qaclick123").header("Content-Type", "application/json")
        .body("{\r\n" + 
				"\"place_id\":\""+rawJsonToString(response,"place_id")+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
       when().put("/maps/api/place/update/json").
       then() .log().all() .assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
       
       ////get
       
        String getResponse =  given().log().all().queryParam("key","qaclick123").
        queryParam("place_id",rawJsonToString(response,"place_id"))
        .when().post("maps/api/place/get/json")
        .then() .log().all() .assertThat().statusCode(200).extract().response().asString();
        
        
        String actual_address = rawJsonToString(getResponse,"address");
        System.out.println(actual_address);
        Assert.assertEquals(actual_address, newAddress);
        
        /*
		 * //Define the specification of request. Server is specified by baseURI above.
		 * RequestSpecification httpRequest = RestAssured.given();
		 * 
		 * //Makes calls to the server using Method type. Response response =
		 * httpRequest.request(Method.GET, "2");
		 * 
		 * //Checks the Status Code int statusCode = response.getStatusCode();
		 * Assert.assertEquals(statusCode, 200);
		 */
    }
}
