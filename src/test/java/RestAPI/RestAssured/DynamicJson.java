package RestAPI.RestAssured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {



	@Test
	public void addBook() {

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String  resp=given().log().all().
				header("Content-Type","application/json").
				body(Payload.Addbook()).
				when().log().all().
				post("/Library/Addbook.php").
				then().assertThat().statusCode(200).
				extract().response().asString();

		JsonPath js= ReUsableMethods.rawToJson(resp);
		String id=js.get("Msg");
		System.out.println(id);

		//deleteBOok 

	}

	/*
	 * @DataProvider(name="BooksData") public Object[][]Â getData() {
	 * //array=collection of elements //multidimensional array= collection of arrays
	 * return new Object[][] {‌{"ojfwty","9363"},{"cwetee","4253"}, {"okmfet","533"}
	 * }; }
	 */





}
