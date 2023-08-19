package PUTAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

public class UpdateUser {
	
	
	//create a user with post call --->userId
	//update the user  ---userId
	@Test
	public void updateUser(){
		
		
			RestAssured.baseURI = "https://gorest.co.in/";
			User user = new User("Shilpa", getRandomEmailId(), "female", "active");

			// 1. add user Post
			int userId = given().log().all().contentType(ContentType.JSON).body(user)
					.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
					.when()
					.post("/public/v2/users/").then().log().all().assertThat().statusCode(201).and().extract()
					.path("id");

			// Get the same user and verify it :GET

			given().log().all()
					.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
					.when().log().all()
					.get("/public/v2/users/" + userId).then().assertThat().statusCode(200).and()
					.body("name", equalTo(user.getName())).and().body("email", equalTo(user.getEmail())).and()
					.body("status", equalTo(user.getStatus()));

		}
		
		
	

	private String getRandomEmailId() {
		// TODO Auto-generated method stub
		return null;
	}

}
