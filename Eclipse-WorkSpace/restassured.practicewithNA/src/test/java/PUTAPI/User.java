package PUTAPI;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data               //for getters and setters
@NoArgsConstructor  //for default constructor
@AllArgsConstructor //for all argument constructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	@JsonProperty("id")
	private Integer id;  //new id is created for every post call
	
	
	//created constructor without constructor
	public User(String name, String email, String gender, String status) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}

	@JsonProperty("name")		//you can use any other name
	private String name;    //exactly same as json without jakson
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("status")
	private String status;
	
	
}
