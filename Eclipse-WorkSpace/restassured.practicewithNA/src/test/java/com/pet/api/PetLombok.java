package com.pet.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               //for getters and setters
@NoArgsConstructor  //for default constructor
@AllArgsConstructor //for all argument constructor
@Builder
public class PetLombok {
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tag;
	private String status;
	
	
	
	@Data               //for getters and setters
	@NoArgsConstructor  //for default constructor
	@AllArgsConstructor //for all argument constructor
	@Builder
	public static class Category{
		
		private Integer  id;
		private String name;
		
	}
	
	@Data               //for getters and setters
	@NoArgsConstructor  //for default constructor
	@AllArgsConstructor //for all argument constructor
	@Builder	
public static class Tag{
		
		private Integer id;
		private String name;
	}
	
	
}
