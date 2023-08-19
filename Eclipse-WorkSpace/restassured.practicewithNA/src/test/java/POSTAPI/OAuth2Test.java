package POSTAPI;

import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import org.testng.annotations.*;
import io.restassured.path.json.*;
import io.restassured.response.Response;

public class OAuth2Test {

	
	
	
	//Auth 2.0 authorization is used post method is used to get the access-token using client key and secret key
	
	static String accessToken;
	
	@BeforeMethod
	public void postTokenAccessTest() {

		RestAssured.baseURI = "https://test.api.amadeus.com";
		 accessToken = given().log().all()
				 .header("Content-Type", "application/x-www-form-urlencoded")
			
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "26tXy7Bq7V1oOsx7WbBC8sj0xrVbzAq3")
				.formParam("client_secret", "wcjiwOAUfWHZlA93")
				.when().log().all()
				.post("/v1/security/oauth2/token")
				.then().assertThat()
				.statusCode(200)
				.extract()
				.path("access_token");
		 		System.out.println(accessToken);
	
	}
	
	
	//get flight info

	@Test
	public void getFlightInfoTest() {
		RestAssured.baseURI = "https://test.api.amadeus.com";
		Response getFlightInfoResponse = given().log().all()
				.header("Authorization", "Bearer " + accessToken)
				.queryParam("origin", "PAR").queryParam("maxPrice", "200")
				.when().log().all()
				.get("/v1/shopping/flight-destinations")
				.then().assertThat()
				.statusCode(200).and()
				.header("Content-Type", "application/vnd.amadeus+json")
				.and().extract().response();

		JsonPath js = getFlightInfoResponse.jsonPath();  //imp
		String type = js.getString("data[0].type");
		System.out.println(type);
		float price = js.getFloat("data[0].price.total");
		System.out.println(price);

		// total price list homework

		List<Float> priceList = new ArrayList<Float>();
		priceList = (js.getList("data.price.total"));
		System.out.println("list of price - > " + priceList);
	}
	
}
