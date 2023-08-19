package com.user.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

//@Jacksonized
//@Data               //for getters and setters
//@NoArgsConstructor  //for default constructor
//@AllArgsConstructor //for all argument constructor
//@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class User {

	@JsonProperty("id")	//this annotation from jackson library is used to use your own name for this property just to avoid any error
	private Integer id;  //new id is created for every post call
	
	@JsonProperty("name")		//you can use any other name
	private String name;    //exactly same as json without jakson
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("status")
	private String status;
	
	
	//created constructor without id 
	public User(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}

	@JsonCreator
	public User() {
		
	}
	
}

