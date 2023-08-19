package GETAPI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.http.Header;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;

public class GETAPIRequestTest {
	
	RequestSpecification req;  // Request specification variable
		
	@BeforeTest
	public void setup() {
		
	RestAssured.baseURI = "https://gorest.co.in"; // base url
	req=RestAssured.given();
	req.headers("Authorization", "Bearer c451d97566a0c57ff532a575acf3199d2fbe3b45f68bbd3807cfb41211177284");
		
	}
	
		@Test
		public void getUsersAPITest() {
			
			// we have fetch status code
			Response response = req.get("/public/v2/users"); // base uri will ad with these query param
			 
			
			// status code is stored in variable and printed on console
			int statuscode = response.statusCode();
			System.out.println("Status code " + statuscode);

			// fetch status message
			String statusmsg = response.statusLine();
			System.out.println("Status messsage " + statusmsg);

			// print the whole response of GET response on console
			System.out.println(response.prettyPrint());

			// response headers
		List<Header> headersList= response.headers().asList();
			System.out.println(headersList.size());
			for (Header h : headersList) {
				System.out.println(h.getName() + h.getValue());
			}
		}


		@Test
		public void getUserwithQueryParamWithHashMapAPITest() {
			//this is request part whatever we send as query parameters
			// req.queryParams("name", "Naveen");
			// req.queryParams("status", "active"); 

			// Storing query parameters in HashMap and passing thet hashmap along with
			// request
			Map<String, String> queryParamMap = new HashMap<String, String>();
			queryParamMap.put("status", "active");
			queryParamMap.put("gender", "male");
			req.queryParams(queryParamMap);

			// =====================================================================

			// base uri will add with these query param and send the response
			Response response = req.get("/public/v2/users"); 

			// we have fetch status code
			int statuscode = response.statusCode(); // status code is stored in variable
			System.out.println("Status code " + statuscode);

			// fetch status message
			String statusmsg = response.statusLine();
			System.out.println("Status messsage " + statusmsg);

			// print the whole response of GET response on console
			System.out.println(response.prettyPrint());

			// to fetch all headers from response headers
			List<Header> headerlist = response.headers().asList();
			System.out.println(headerlist.size());
			for (Header h : headerlist) {
				System.out.println(h.getName() + h.getValue());
			}
		}

	}




