package com.product.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // for getters and setters
@NoArgsConstructor // for default constructor
@AllArgsConstructor // for all argument constructor
@Builder
public class ProductLombok {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	@Data // for getters and setters
	@NoArgsConstructor // for default constructor
	@AllArgsConstructor
	@Builder
	public static class Rating {
		private double rate;
		private double count;
		
	}
}
