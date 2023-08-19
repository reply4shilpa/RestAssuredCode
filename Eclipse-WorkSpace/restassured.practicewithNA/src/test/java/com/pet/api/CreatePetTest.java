package com.pet.api;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreatePetTest {

	
	//without builder pattern

	
	@Test
	public void createPetTest() {
		
	RestAssured.baseURI = "https://petstore.swagger.io/";
	
	Category category= new Category(1, "Dog");
	
	List<String>photoUrls=	Arrays.asList("https://dog1.com","https://hon.com");
	
	Tag tag1=new Tag(1, "puppy");
	Tag tag2=new Tag(2, "lap puppy");
	List<Tag>tagList=Arrays.asList(tag1,tag2);
		
		PetLombok pet = new PetLombok(300, category, "Ronney", photoUrls, tagList,"available" );

		// 1. add user Post
		Response response= 
							RestAssured.given().log().all()
							.contentType(ContentType.JSON)
							.body(pet)    //serialization  /Marshelling
							.when()
							.post("/v2/pet");
		
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		
		
		//de-serialization
		ObjectMapper mapper=new ObjectMapper();
		
		PetLombok petResponse;
		try {
				petResponse = mapper.readValue(response.getBody().asString(), PetLombok.class);
			
				System.out.println(petResponse.getId());
				System.out.println(petResponse.getName());
				System.out.println(petResponse.getStatus());
				System.out.println(petResponse.getCategory().getId());
				System.out.println(petResponse.getCategory().getName());
				System.out.println(petResponse.getTag());
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//Get method to check
	}
		
		@Test
		public void CreatePetWithBuilderPattern_Test() {
			
				RestAssured.baseURI = "https://petstore.swagger.io/";
				
			
				Category category=new Category.CategoryBuilder()
						.id(20)
						.name("puppy")
						.build();
				
				Tag tag1=new Tag.TagBuilder()
						.id(50)
						.name("blue")
						.build();
				Tag tag2=new Tag.TagBuilder()
						.id(50)
						.name("red")
						.build();
				
	PetLombok pet=	new PetLombok.PetLombokBuilder()
						.id(500)
						.category(category)
						.name("Bella")
						.photoUrls(Arrays.asList("http://www.dog1.com","http://www.dog2.com"))
						.tag(Arrays.asList(tag1, tag2))
						.status("available")
						.build();
	
	Response response=RestAssured.given()
			.contentType(ContentType.JSON)
			.body(pet)
			.when()
			.post("/v2/pet");
	
	System.out.println(response.statusCode());
	
	ObjectMapper mapper=new ObjectMapper();
	
	try {
			PetLombok petres=mapper.readValue(response.getBody().asString(), PetLombok.class);
			Assert.assertEquals(pet.getName(), petres.getName());
	
	
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
				}
		
		
	}


