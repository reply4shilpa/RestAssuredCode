package XMLAPIXMLAPITest;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetCircuit {
	
	public void xmlTest() {
	
	RestAssured.baseURI = "http://ergast.com"; 
	
	Response response=RestAssured.given()
	.when().get("/api/f1/2017/circuits.json")
	.then().extract().response();
	
	String resBody=response.body().asString();
	
	//Create object of xmlPAth:
	
	XmlPath xmlPath= new XmlPath(resBody);
	List<String>circuitNames=xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
	for(String e:circuitNames)
	System.out.println(e);
	System.out.println("-------------------------------------------------------------------");
	
	List<String>circuitIds=xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");
	for(String e:circuitNames)
	System.out.println(e);
	
	System.out.println("-------------------------------------------------------------------");
	
	//Fetch locality where circuitids= USA
	//RA using groovy internally
	
	String locality=xmlPath.get("**.findAll{it.circuitId=='americas'}.Location.Locality").toString();
	System.out.println(locality);
	/***
	 * -deepscan xml process it-check each circuitid is americas
	 */
	
	
	System.out.println("-------------------------------------------------------------------");
	
	// finding out attributes long and lat
	String latVal=xmlPath.get("**.findAll{it.circuitId=='americas'}.Location.@lat").toString();
	String longVal=xmlPath.get("**.findAll{it.circuitId=='americas'}.Location.@long").toString();
	System.out.println("Lat value: "+latVal+"Long val:-> "+longVal);
	
	System.out.println("-------------------------------------------------------------------");
	
	//Fetch the locality where circuitid =baharain and circuitid =americas
	
	String data=xmlPath.get("**.findAll{it.circuitId=='americas' &&  it.circuitId=='baharain'}.Location.locality").toString();
	System.out.println(data);
	
	
	System.out.println("-------------------------------------------------------------------");
	//fetch circuitname where circuitid is americas
	String circuitName=xmlPath.get("**.findAll{it.circuitId=='americas'}.CircuitName").toString();
	
	
	System.out.println("-------------------------------------------------------------------");
	//fetch urls where circuitid is americas
	String url=xmlPath.get("**.findAll{it.circuitId=='americas'}.@url").toString();
	System.out.println(url);
}
}