package GETAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GetAPIWithBDD {
	@Test
	public void getProductTest() {

		// static io.restassured.RestAssured.*; these methods coming from this class
		given().log().all().
		when()
			.get("https://fakestoreapi.com/products")
				.then().log().all()
					.assertThat()// at	 this 		// poin // we		
								.statusCode(200).and().contentType(ContentType.JSON).and().header("Connection", "keep-alive").and()
			
								// $ is a exact body if there is array body we use $ sign
				.body("$.size()", equalTo(20)).and().body("id", is(notNullValue())).and()
				.body("title", hasItem("Mens Cotton Jacket"));
	}

	@Test
	public void getUSerAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when().get("/public/v2/users").then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.body("$.size()", equalTo(10));

	}

	@Test
	public void getAPITESTWithQueryParameters() {

		RestAssured.baseURI = "https://fakestoreapi.com"; // good practice to

		given()
			.queryParam("limit", 5).when().get("/products")
				.then()
					.assertThat().statusCode(200)
						.and()
							.contentType(ContentType.JSON);

	}

	@Test
	public void getAPITESTWithTwoQueryParameters() {

		RestAssured.baseURI = "https://fakestoreapi.com"; // good practice to

		given().queryParam("limit", 3).queryParam("sort", "desc").when().get("/products").then().log().all()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON);

	}

	@Test
	public void getAPITEST_With_ExtractBody() {

		RestAssured.baseURI = "https://fakestoreapi.com"; // good practice to

		// response is stored in Response response, output of GET method is response
		// varidatable
		Response response = given().queryParam("limit", 3).queryParam("sort", "desc").when().get("/products");

		JsonPath jp = response.jsonPath();
		int firstProductId = jp.getInt("[0].id");
		System.out.println(firstProductId);

		String firstProductName = jp.getString("[0].title");
		System.out.println(firstProductName);

		float price = jp.getFloat("[0].price");
		System.out.println(price);

		int count = jp.getInt("[0].rating.count");
		System.out.println(count);

		// collect all the ids from entire jsonarray

	}

	@Test
	public void getAPITEST_With_ExtractBody_WithArray() {

		RestAssured.baseURI = "https://fakestoreapi.com"; // good practice to

		// response is stored in Response response, output of GET method is response
		// varidatable
		Response response = given().queryParam("limit", 10).queryParam("sort", "desc").when().get("/products");

		JsonPath jp = response.jsonPath();

		// collect all the ids from entire jsonarray
		List<Integer> idList = jp.getList("id", Integer.class); //

		List<String> titleList = jp.getList("title");

		List<Float> ratingList = jp.getList("rating.rate", Float.class);

		List<Integer> countList = jp.getList("rating.count");

		for (int i = 0; i < 10; i++) {
			int id = idList.get(i);
			String title = titleList.get(i);
			float rate = ratingList.get(i);
			int count = countList.get(i);

			System.out.println("ID: " + id + " title: " + title + " rate: " + rate + " count : " + count);

		}
	}

	@Test    //HOMEWORK
	public void getAPITEST_With_ExtractBody_WithArray_ForGORest() {

		RestAssured.baseURI = "https://gorest.co.in"; // good practice to

		Response response = given().when().get("/public/v2/users");

		JsonPath jp = response.jsonPath();  //json array

		List<Integer> idList = jp.getList("id", Integer.class); //

		List<String> nameList = jp.getList("name");

		List<String> emailList = jp.getList("email");

		for (int i = 0; i < 10; i++) {
			int id = idList.get(i);
			String name = nameList.get(i);
			String email = emailList.get(i);

			System.out.println("ID: " + id + " Name: " + name + " Email: " + email);

		}
	}
	
	
	@Test    
	public void getAPITEST_With_ExtractBody_WithJson_ForGORest() {

		RestAssured.baseURI = "https://gorest.co.in"; // good practice to

		Response response = given()
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when().get("/public/v2/users/3630525");

		JsonPath jp = response.jsonPath();

		System.out.println("ID: " +jp.getInt("id") + " Name: " + jp.getString("name") + " Email: " + jp.getString("email"));

		}
	
	//To fetch single value we use EXTRACT method
	@Test    
	public void getAPITEST_With_ExtractBody_WithJson_ExtractMethod() {

		RestAssured.baseURI = "https://gorest.co.in"; // good practice to

		int UserId = given()
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when().get("/public/v2/users/3630525").then().extract().path("id");

System.out.println(UserId);
		}
	

@Test    
public void getAPITEST_With_ExtractBody_WithJson_ExtractMethod1() {

	RestAssured.baseURI = "https://gorest.co.in"; // good practice to

	Response response = given()
			.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
			.when().get("/public/v2/users/3630525").then().extract().response();

	System.out.println(" ID "+response.path("id") + " Email: " +response.path("email"));
	}
}

