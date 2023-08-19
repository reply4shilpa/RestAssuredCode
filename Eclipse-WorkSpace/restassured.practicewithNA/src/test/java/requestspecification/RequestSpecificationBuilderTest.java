package requestspecification;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;



public class RequestSpecificationBuilderTest {

	RequestSpecification reqSpec =	  new RequestSpecBuilder()
			.setBaseUri("https://gorest.co.in")
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
			.build();

	@Test
	public void getAPIwithRequestSpec() {
		RestAssured.given().spec( reqSpec)
				.get("/public/v2/users").then().statusCode(200);
	}

}


