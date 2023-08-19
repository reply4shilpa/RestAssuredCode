package POSTAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

//POJO- Plain Old Java Object
//can not extend any other class
//oop-we have to use encapsulation --hiding data members 

//serialization-java object to other format: file, media, json/xml/text/pdf
//pojo to json-serialization - Jackson api is added in dependency in pom.xml

public class BookingAuthWithPojoTest {
	
	
	@Test
	public void getBookingAuthTokenTest() {
	RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	Credentials cred= new Credentials("admin","password123");		//serialization happens here
	
	String tokenId = given().log().all().contentType(ContentType.JSON)
			.body(cred)//
			.when()
			.post("/auth").then().log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("token");
	
	System.out.println(tokenId);
	Assert.assertNotNull(tokenId);
}
}