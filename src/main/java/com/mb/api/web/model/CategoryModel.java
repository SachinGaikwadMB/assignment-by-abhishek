package com.mb.api.web.model;

import jakarta.validation.constraints.NotBlank;

public class CategoryModel
{
	@NotBlank
	private String categoryName;
	
	public CategoryModel() {}

	public CategoryModel(String categoryName)
	{
		super();
		this.categoryName = categoryName;
	}

	public String getCategoryName()
	{
		return categoryName;
	}
	
	
}
