package LatestRestAssured;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
public class RestMethods {
	
	private static String requestBody = "{\n" +
        "  \"title\": \"foo\",\n" +
        "  \"body\": \"bar\",\n" +
        "  \"userId\": \"1\" \n}";

@BeforeAll
public static void setup() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
}

@Test
public void postRequest() {
    Response response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .extract().response();

    Assertions.assertEquals(201, response.statusCode());
    Assertions.assertEquals("foo", response.jsonPath().getString("title"));
    Assertions.assertEquals("bar", response.jsonPath().getString("body"));
    Assertions.assertEquals("1", response.jsonPath().getString("userId"));
    Assertions.assertEquals("101", response.jsonPath().getString("id"));
}

}
