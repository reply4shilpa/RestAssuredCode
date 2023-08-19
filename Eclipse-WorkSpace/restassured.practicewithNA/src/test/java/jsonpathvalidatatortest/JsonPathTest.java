package jsonpathvalidatatortest;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import com.jayway.jsonpath.*;

import java.util.List;
import java.util.Map;

public class JsonPathTest {
	@Test
	public void getCircuitDAtaAPIWith_YearTest() {

		RestAssured.baseURI = "https://ergast.com";

		Response response = RestAssured.given().log().all()
				.when()
				.get("/api/f1/2017/circuits.json");

				String jsonResponse = response.asString();

				System.out.println(jsonResponse);

		// length() is present in JsonPath so used to find total no of circuits.
		// read method in JsonPath read the jsonResponse and extract the second value
		// given in string
		int totalCircuit = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		
		
		//$.MRData.CircuitTable.Circuits.circuitId[*]=='Bahrain'

		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");

		System.out.println(countryList);
		System.out.println(totalCircuit);
	}

	@Test
	public void getProductTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().when().get("/products");
		String jsonString = response.asString(); // returns body as String

		List<Float> rateLessThanThree = JsonPath.read(jsonString, "$[?(@.rating.rate<3)].rating.rate");
		// generics is always class---Float class

		System.out.println(jsonString);
		System.out.println(rateLessThanThree);

		System.out.println("--------------------------------------");

		// fetching 2 attributes in this case title, price
		List<Map<String, Object>> jweleryList = JsonPath.read(jsonString,
				"$[?(@.category=='jewelery')].[\"title\",\"price\"]");
		
		//$.MRData.CircuitTable.Circuits[?(@.Location='sakhir')].[\"circuitId\",\"circuitName\"]
		
		// Object generics is used when we are not sue about primitive datatypes, we are
		// geting list of Maps
		System.out.println(jweleryList);
		for (Map<String, Object> product : jweleryList) {

			String title = (String) product.get("title");
			Object price = (Object) product.get("price");

			System.out.println(" Title:-> " + title + " price:-> " + price);
		}
		System.out.println("--------------------------------------");

		// Fetching 3 attributes in this case title, price, id
		List<Map<String, Object>> jeweleryList2 = JsonPath.read(jsonString,
				"$[?(@.category=='jewelery')].[\"title\",\"price\",\"id\"]");

		for (Map<String, Object> product : jeweleryList2) {

			String title = (String) product.get("title");
			Object price = (Object) product.get("price");
			Integer id = (Integer) product.get("id");

			System.out.println(" Title:-> " + title + " price:-> " + price + " id:-> " + id);

		}
		System.out.println("--------------------------------------");
		
		// Fetching 4 attributes $[?(@.category=='jewelery')].[title,price,count,rate]
		// Used List to store List of Maps where we have 4 keys and their values :do not support

		List<Map<String, Object>> jeweleryList3 = JsonPath.read(jsonString,
				"$[?(@.category=='jewelery')].[\"title\",\"price\",\"count\",\"rate\"]");

		for (Map<String, Object> product : jeweleryList3) {

			String title = (String) product.get("title");
			Object price = (Object) product.get("price");
			Integer count = (Integer) product.get("count");
			Object rate = (Object) product.get("rate");

			System.out.println(" Title:-> " + title + " price:-> " + price + " count:-> " + count + " rate ->" + rate);
		}
	}

}
