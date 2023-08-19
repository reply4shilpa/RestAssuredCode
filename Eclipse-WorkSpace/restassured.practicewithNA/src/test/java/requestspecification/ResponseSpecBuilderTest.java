package requestspecification;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseSpecBuilderTest {


	@Test
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
			.expectContentType(ContentType.JSON)
			.expectStatusCode(401)
			.expectHeader("Server","cloudflare")
			.build();
		
		return responseSpec_401_fail;
						
	}
	
	@Test
	public void get_user_res_200_spec_test() {
		
		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284")
						.when()
								.get("/public/v2/users")
										.then().log().all()
												.assertThat()
														.spec((get_User_With_res_Spec_200_ok()));
		
	}
	
	
	@Test
	public void get_user_res_401_Auth_fail_spec_test() {
		
		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284rrrrr")
						.when()
								.get("/public/v2/users")
										.then().log().all()
												.assertThat()
														.spec((get_User_With_res_Spec_401_Auth_fail()));
		
	}
	
}
