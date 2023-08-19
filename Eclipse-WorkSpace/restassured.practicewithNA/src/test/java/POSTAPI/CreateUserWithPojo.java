package POSTAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class CreateUserWithPojo {

	// 1. directly supply the json string
	// pass the json file
	// 3. java object--to json with the help of jackson

	
	//creating unique email everytime
	public static String getRandomEmailId() {

		return "apiautomation" + System.currentTimeMillis() + "@mail.com";
	}

	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in/";
		User user = new User("Shilpa", getRandomEmailId(), "female", "active");   //User is a POJO class whose instance is created

		// 1. add user with POST call
		int userId = given().log().all()
				.contentType(ContentType.JSON)
				.body(user)
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when()
				.post("/public/v2/users/")
				.then().log().all()
				.assertThat()
				.statusCode(201).and()
				.extract()
				.path("id");

		// Get the same user and verify it :GET

		given().log().all()
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when().log().all()
				.get("/public/v2/users/" + userId)
				.then()
				.assertThat()
				.statusCode(200).and()
				.body("name", equalTo(user.getName())).and()
				.body("email", equalTo(user.getEmail())).and()
				.body("gender", equalTo(user.getGender()))
				.body("status", equalTo(user.getStatus()));
		
		
		//update the user with Put call
		
		given().log().all().contentType(ContentType.JSON).body(user)
		.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
		.when()
		.put("/public/v2/users/"+userId)
		.then()
		.assertThat()
		.body("id", equalTo(userId));
		
		
	}
}
