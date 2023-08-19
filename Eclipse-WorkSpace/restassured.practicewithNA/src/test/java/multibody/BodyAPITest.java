package multibody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyAPITest {
	
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI="http://httpbin.org";
		String payload="hi thi is shilpa";
		
		Response response=	RestAssured.given()
		.contentType(ContentType.TEXT)
		.body(payload)
		.when()
		.post("/post");
		
		RestAssured.given()
				.contentType(ContentType.TEXT)
				.get();
		
		
	}
	@Test
	public void bodyWithJSTest() {}
	
	@Test
	public void bodyWithHTMLTest() {}
	
	@Test
	public void bodyWithXMLTest() {}
	
	
	@Test
	public void bodyWithFormDataMultiPartTest() {
		
		RestAssured.baseURI="http://httpbin.org";

		Response response=	RestAssured.given()
		.contentType(ContentType.MULTIPART)  //key and value pair format
		.multiPart("name", "testing")
		.multiPart("filename", new File(""))  //as many files you can attach
		.when()
		.post("/post");
	}

	@Test
	public void bodyWithBinaryFileTest() {
		
		RestAssured.baseURI="http://httpbin.org";

		Response response=	RestAssured.given()
	//	.contentType(ContentType.)  //key and value pair format
				.header("","")    //only one file you can attach
				.body(new File (""))
		
		.when()
		.post("/post");
	}
	
}
