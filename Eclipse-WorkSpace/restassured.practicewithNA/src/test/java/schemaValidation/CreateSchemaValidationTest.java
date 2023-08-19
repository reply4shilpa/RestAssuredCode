package schemaValidation;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class CreateSchemaValidationTest {
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in/";
		// 1. add user Post
		given().log().all()
				.contentType(ContentType.JSON)
				.body(new File("./src/test/resource/testdata/addUser.json"))
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
				.when()
				.post("/public/v2/users/")
				.then()
				.statusCode(200)
				.and()
				.body(matchesJsonSchemaInClasspath("scemavalidation.json"));

}
}