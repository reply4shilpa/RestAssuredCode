package DeleteAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


//post-create user--201
//delete- created user--204
//get = fetch same user id--404

public class DeleteUsrTest {

	private String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@mail.com";
	}
	
	
	@Test
	public void deleteUserTest() {
		
	RestAssured.baseURI = "https://gorest.co.in/";
			
	User user = new User("Shilpa", getRandomEmailId(), "female", "active");

			// 1. add user Post
			int userId = given().log().all().contentType(ContentType.JSON)
					.body(user)
						.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
							.when()
							.post("/public/v2/users/").then().log().all()
								.assertThat()
									.statusCode(201)
										.and()
											.extract()
												.path("id");

			// Get the same user and verify it :GET

			given().log().all()
					.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
						.when().log().all()
							.get("/public/v2/users/" + userId)
								.then().log().all()
									.assertThat()
										.statusCode(200)
											.and()
												.body("name", equalTo(user.getName()))
													.and()
														.body("email", equalTo(user.getEmail()))
															.and()
																.body("status", equalTo(user.getStatus()));

		

	
	//2. Delete 
		
		RestAssured.given().log().all()
			.contentType(ContentType.JSON)
				.body(user)
					.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
						.when()
							.delete("/public/v2/users/"+userId).then().log().all()
								.assertThat().statusCode(204);
		
		//Get the user..we should not get the user
		
		RestAssured.given().log().all()
		.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
			.when().log().all()
				.get("/public/v2/users/" + userId)
					.then().log().all()
						.assertThat()
							.statusCode(404);
							//	.and()
							//		.body("name", equalTo(user.getName()))
								//		.and().body("email", equalTo(user.getEmail()))
							//				.and()
							//					.body("status", equalTo(user.getStatus()));
		

}}

