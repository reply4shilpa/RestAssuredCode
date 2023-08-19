package com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;

//Example of Serialization and de-serialization with single Lombok POJO

public class CreateUserWithLombok {
	
	
	public static String getRandomEmailId() {

		return "apiautomation" + System.currentTimeMillis() + "@mail.com";
	}

	@Test
	public void createUserTest() {
	RestAssured.baseURI = "https://gorest.co.in/";
	User user = new User("shilpa", getRandomEmailId(), "female","active");

	// 1. add user Post
	Response response= RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
			.body(user)
			.when()
			.post("/public/v2/users/");
	
	Integer userId = response.jsonPath().get("id");
	System.out.println("user id " +userId);
		
	//Get method to varify the same user
	
	
	Response getResponse = RestAssured.given().log().all().contentType(ContentType.JSON)
			.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when()
					.log().all()
						.get("/public/v2/users/" + userId);

	System.out.println(getResponse.prettyPrint());
	
	// de-serialization....now more people use jsonpath for

	ObjectMapper mapper = new ObjectMapper(); // ObjetMapper class is used for de serialization

	try {
		User userResponse = mapper.readValue(getResponse.getBody().asString(), User.class);

		System.out.println(userResponse.getName());
	//	System.out.println(userResponse.get);
		Assert.assertEquals(user.getName(), userResponse.getName());
		Assert.assertEquals(user.getEmail(), userResponse.getEmail());
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
}
	// de-serialization

	// userResponse.getEmail()+userResponse.getName());
	
	
	

	}
