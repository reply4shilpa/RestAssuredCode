package oathAPIs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTest {

	@BeforeTest
	public void alluresetup() {
		
	}
	
	
	@Test
	public void basicAuthWithJsonBody() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		String jwtTokenId = RestAssured.given().contentType(ContentType.JSON).body("").when().post("/auth/login").then()
				.assertThat().statusCode(200).and().extract().path("token");

	}

	public class basicAuthTest {

		@Test
		public void basicAuthWithJsonBody() {
			RestAssured.baseURI = "https://the-internet";

			String responseBody = RestAssured.given().contentType(ContentType.JSON).auth().basic("admin", "admin") // 1.
																													// Basic
																													// Auth
					.when().get("/basic_auth").then().assertThat().statusCode(200).and().extract().body().asString();

		}

		public class preemitiveAuthTest {

			@Test
			public void basicAuthWithJsonBody() {
				RestAssured.baseURI = "https://the-internet";

		String responseBody = RestAssured.given().contentType(ContentType.JSON).auth().preemptive()
						.basic("admin", "admin") // 2. for preemtive oauth
						.when().get("/basic_auth")
						.then().assertThat().statusCode(200).and().extract().body()
						.asString();

			}

			public class disestiveAuthTest {
				
				@Test
				public void basicAuthWithJsonBody() {
					RestAssured.baseURI="https://the-internet";
				
		String responseBody=RestAssured.given()
					.contentType(ContentType.JSON)
					.auth().digest("admin", "admin") //3. Basic auth:  digest oauth
					.when()
					.get("/basic_auth")
					.then()
					.assertThat()
					.statusCode(200)
					.and()
					.extract().body().asString();
					
					
					
				}
				
				
				@Test
				public void apiKeyAuthTest() {
					RestAssured.baseURI="api.weatherapi.com";
				
		String responseBody=RestAssured.given()
				.queryParam("q", "London")
				.queryParam("aqi", "no")
				.queryParam("q", "London")
					.when()
					.get("/")
					.then()
					.assertThat()
					.statusCode(200)
					.and()
					.extract().body().asString();
					
					
					
					
					
					
					
				}}}}}
