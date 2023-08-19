package POSTAPI;

import static io.restassured.RestAssured.given;
import org.testng.annotations.*;
import io.restassured.http.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import java.io.*;

public class BookingAuthTest {
	//int userId;

	@Test
	public void getBookingTokenTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given().log().all().contentType(ContentType.JSON)
				.body(new File("./src/test/resource/testdata/basicauth.json")) //
				.when().post("/auth").then().log().all().assertThat().statusCode(200).extract().path("token");

		System.out.println(tokenId);

	}

	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in/";
		// 1. add user Post
		int userId = given().log().all()
				.contentType(ContentType.JSON)
				.body(new File("./src/test/resource/testdata/addUser.json"))
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when()
				.post("/public/v2/users/")
				.then().log().all()
				.assertThat()
				 .statusCode(201)
				.and()
				.body("name", equalTo("subodh"))
				.extract().path("id");
		System.out.println("user id->" + userId);

		// 2. get same user id and fetch the record

		given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when().get("/public/v2/users/" + userId).then().assertThat().statusCode(200).and()
				.body("name", equalTo("subodh")).and().body("id", equalTo(userId));

	}

}
