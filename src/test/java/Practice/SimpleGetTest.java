package Practice;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

	
	@Test
	public void validateResponseJSONBody_test()
	{
	    RestAssured.baseURI = "http://localhost:8888";
	    RequestSpecification httpRequest = RestAssured.given();
	    // Set HTTP Headers
	    httpRequest.header("Content-Type", "application/json");
	    httpRequest.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiZXhwIjoxNTI5NDIwMjQzfQ.qepmpAizfH9QHNygKM-7fhhkpvJSYMUOQOTKemLeYCcy2E1yrFNxA61YCqio7rWGUmSz3KE2osqlo-7LhWzRTQ");
	    
	    Response response = httpRequest.get("/users/Y3vWa8QwjK0DW6NmvB4HsXIV9gHg1q");
	    
	    // Get Response Body 
	    ResponseBody body = response.getBody();
	    
	    // Get Response Body as String 
	    String bodyStringValue = body.asString();
	    
	    System.out.println(bodyStringValue);
	    // Validate if Response Body Contains a specific String
	    Assert.assertTrue(bodyStringValue.contains("firstName"));
	    
	    // Get JSON Representation from Response Body 
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    // Get specific element from JSON document 
	    String firstName = jsonPathEvaluator.get("firstName");
	    // Validate if the specific JSON element is equal to expected value
	    Assert.assertTrue(firstName.equalsIgnoreCase("Sergey"));
	}

}