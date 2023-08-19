package GETAPI;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetAPIWithPathParameters {

	// Path parameters are mentioned followed by forward slash whereas query
	// parameters followed by question mark
	// Path parameters are mostly inside the URI
	// In Postman /:/ (comes after forward slash the colon) added in uri

	@Test
	public void getCircuitsDataWithQueryParameters() {

		RestAssured.baseURI = "https://ergast.com"; // good practice to

		given()
		.pathParam("year", "2019")
		.when().get("/api/f1/{year}/circuits.json")
		.then().assertThat().statusCode(200)
				.and()
				.body("MRData.CircuitTable.season", equalTo("2019"))
				.and()
				.body("MRData.CircuitTable.Circuits.circuitsId", hasSize(21))
				.and()
				.body("MRData.total", equalTo("21"));
	}

	// Datadriven testing using RA

	@DataProvider
	public Object[][] getCircuitData() {

		// data could be String or numaric so we are using Object array

		return new Object[][] {

				{ "2016", 21 }, { "2017", 20 }, { "2023", 22 }, { "1966", 9 }

		};
	}

	@Test(dataProvider = "getCircuitData") // here we are providing the function providing data
	public void getCircuitsDataWith_DataProvider_QueryParameters(String seasonYear, int totalCircuits) {

		RestAssured.baseURI = "https://ergast.com"; // good practice to give only domain name here

		given()
		.pathParam("year", seasonYear)
		.when()
		.get("/api/f1/{year}/circuits.json").then().assertThat()
				
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.season", equalTo(seasonYear))
		.and()
		.body("MRData.CircuitTable.Circuits.circuitsId", hasSize(totalCircuits));

	}

	@Test     //HOMEWORK
	public void getAPITEST_With_ExtractBody_WithArray() {

		RestAssured.baseURI = "https://ergast.com"; // good practice to give only domain name here

		Response response = given().when().get("/api/f1/circuits.json");

		JsonPath jp = response.jsonPath();
		// collect all the ids from entire jsonarray
		List<String> idList = jp.getList("MRData.CircuitTable.Circuits.circuitId", String.class); //

	System.out.println("total no of circuit ids are: -> " + idList.size() );
		
		for (int i = 0; i < idList.size(); i++) {
			String id = idList.get(i);
			System.out.println("ID: " + id);
		}

	}
}
