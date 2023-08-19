package com.product.api;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {

@Test	
public void getProductTest_With_Pojo() {
	
	
	RestAssured.baseURI = "https://fakestoreapi.com";
	Response response= RestAssured.given().log().all()
			.when()
			.get("/products");
			//System.out.println(response);
					
			// json to pojo
	  ObjectMapper mapper = new ObjectMapper();
	  
	  try { Product product[]= mapper.readValue(response.getBody().asString(),
	  Product[].class);
	  
	  for(Product p :product) {
	  
	  System.out.println("ID ->" +p.getId()); 
	  System.out.println("Title -> " +p.getTitle()); 
	  System.out.println("Price : "+ p.getPrice());
	  System.out.println("description- > " +p.getDescription());
	  System.out.println("Category"+ p.getCategory()); 
	  System.out.println("Image"+  p.getImage()); 
	  System.out.println("Rate"+ p.getRating().getCount());
	  System.out.println("Rate"+ p.getRating().getRate());
	 
	  System.out.println("--------------------------");
	  
	  }
	  
	  } catch (JsonMappingException e) { e.printStackTrace(); } catch
	  (JsonProcessingException e) { e.printStackTrace(); }
	 
}

@Test	
public void getProductTest_With_Lombok() {
	
	
	RestAssured.baseURI = "https://fakestoreapi.com";
	Response response= RestAssured.given().log().all()
			.when()
			.get("/products");
			//System.out.println(response);
					
			// json to pojo
	  ObjectMapper mapper = new ObjectMapper();
	  
	  try { 
		  ProductLombok product[]= mapper.readValue(response.getBody().asString(),
	  ProductLombok[].class); 		//de-serialization  -json to POJO
	  
	  for(ProductLombok p :product) {
	  
	  System.out.println("ID: ->" +p.getId()); 
	  System.out.println("Title: -> " +p.getTitle()); 
	  System.out.println("Price: "+ p.getPrice());
	  System.out.println("description: " +p.getDescription());
	  System.out.println("Category: "+ p.getCategory()); 
	  System.out.println("Image: "+  p.getImage()); 
	  System.out.println("Count: "+ p.getRating().getCount());
	  System.out.println("Rate: "+ p.getRating().getRate());
	 
	  System.out.println("--------------------------");
	  
	  }
	  
	  } catch (JsonMappingException e) { e.printStackTrace(); } catch
	  (JsonProcessingException e) { e.printStackTrace(); }
	 
}

@Test	
public void getProductTest_With_Lombok_BuilderPattern() {
	
	
	RestAssured.baseURI = "https://fakestoreapi.com";
	Response response= RestAssured.given().log().all()
			.when()
			.get("/products");
			//System.out.println(response);
					
	
}


}