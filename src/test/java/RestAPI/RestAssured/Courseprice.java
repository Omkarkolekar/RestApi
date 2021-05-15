package RestAPI.RestAssured;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Courseprice {


	public String courseprice() {

		return "{\r\n" + 
				"\"dashboard\": {\r\n" + 
				"\"purchaseAmount\": 910,\r\n" + 
				"\"website\": \"rahulshettyacademy.com\"\r\n" + 
				"},\r\n" + 
				"\"courses\": [\r\n" + 
				"{\r\n" + 
				"\"title\": \"Selenium Python\",\r\n" + 
				"\"price\": 50,\r\n" + 
				"\"copies\": 6\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"title\": \"Cypress\",\r\n" + 
				"\"price\": 40,\r\n" + 
				"\"copies\": 4\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"\"title\": \"RPA\",\r\n" + 
				"\"price\": 45,\r\n" + 
				"\"copies\": 10\r\n" + 
				"}\r\n" + 
				"]\r\n" + 
				"}";

	}

	@Test
	public void getMockedresponse() {

		JsonPath js =new JsonPath(courseprice());

		int count=	js.getInt("courses.size()");
		System.out.println(count);
		//Print Purchase Amount
		int totalAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		//Print Title of the first course

		String titleFirstCourse=js.get("courses[0].title");
		System.out.println(titleFirstCourse);


		String titlethirdCourse=js.get("courses[2].title");
		System.out.println(titlethirdCourse);

		int totalcopies = 0;
		for(int i=0;i<count;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");

			int price =js.get("courses["+i+"].price");

			int copies=js.get("courses["+i+"].copies");
			totalcopies = (price*copies) + totalcopies;

			System.out.println(totalcopies);
		}

	}


}



