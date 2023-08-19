package requestspecification;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpecificationUtilityGETCall {

	public static RequestSpecification user_req_spec() {
	
		RequestSpecification reqSpec =	  new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284??")
				.build();
		
		return reqSpec;
	
	}
	
	
	public static ResponseSpecification get_User_With_res_Spec_200_ok() {
	
	ResponseSpecification responseSpec_200_ok =	  new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server","cloudflare")
			.expectBody("$.size()", equalTo(10))
			.expectBody("id", hasSize(10))
				.build();
		
		return responseSpec_200_ok;
						
	}
	
	@Test
	public static ResponseSpecification get_User_With_res_Spec_401_Auth_fail() {
	
	ResponseSpecification responseSpec_401_fail =	new ResponseSpecBuilder()
			.expectStatusCode(401)
			.expectHeader("Server","cloudflare")
			//.expectHeader("x-pagination-limit","10")
			.expectStatusLine("HTTP/1.1 401 Unauthorized")
	//		.expectBody("$.message", equalTo("Invalid token"))
			.build();
		
		return responseSpec_401_fail;
						
	}
	
	@Test
	public static ResponseSpecification get_User_With_res_Spec_404_Not_found() {
	
	ResponseSpecification responseSpec_404_not_found =	new ResponseSpecBuilder()
			.expectStatusCode(404)
			.expectHeader("Server","cloudflare")
			.expectHeader("x-frame-options","SAMEORIGIN")
			.expectHeader("Content-Type","text/html; charset=utf-8")
			.expectStatusLine("HTTP/1.1 404 Not Found")
		//	.expectBody("title",equalTo("Page Not Found | GO REST"))
			.build();
		
		return responseSpec_404_not_found;
						
	}
	
	@Test
	public void get_user_res_200_spec_test() {
		

		given().log().all()
		.spec( user_req_spec())	
			.when()
				.get("/public/v2/users")   //service url/endpoint
						.then().log().all()
							.assertThat()
								.spec((get_User_With_res_Spec_200_ok()));
		
	}
	
	@Test
	public void get_user_res_401_Auth_fail_spec_test() {
		

		given().log().all()
				.spec( user_req_spec())	
					.when()
						.get("/public/v2/users")
							.then().log().all()
								.assertThat()
									.spec((get_User_With_res_Spec_401_Auth_fail()));
		
	}
	
	
	@Test
	public void get_user_res_404_not_Found_spec_test() {
		

		given().log().all()
				.spec( user_req_spec())	
				.when()
					.get("/public/")  //passing wrong endpoint or no endpoint
						.then()
							.assertThat()
								.spec((get_User_With_res_Spec_404_Not_found()));
		
	}
}
