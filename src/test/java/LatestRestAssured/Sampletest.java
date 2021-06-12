package LatestRestAssured;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class Sampletest {
	
	private static String requestBody = "{\r\n" + 
			"id:2121,\r\n" + 
			"firstName:\"Habeeb\",\r\n" + 
			"lastname:\"Mohamed\",\r\n" + 
			"education:\"K.S.R\",\r\n" + 
			"details:\"{\r\n" + 
			"\"B.E\",\"Automobile\" \r\n" + 
			"}\";\r\n" + 
			"}\r\n" + 
			"";

@BeforeAll
public static void setup() {
    RestAssured.baseURI = "http://localhost:8080/student";
}

@Test
public void postRequest() {
    Response response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .get()
            .then()
            .extract().response();

    System.out.println(response.statusCode());
    System.out.println(response);
		/*
		 * Assertions.assertEquals(201, response.statusCode());
		 * Assertions.assertEquals("foo", response.jsonPath().getString("title"));
		 * Assertions.assertEquals("bar", response.jsonPath().getString("body"));
		 * Assertions.assertEquals("1", response.jsonPath().getString("userId"));
		 * Assertions.assertEquals("101", response.jsonPath().getString("id"));
		 */
}

}
