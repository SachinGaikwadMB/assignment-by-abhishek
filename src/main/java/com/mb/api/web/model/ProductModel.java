package com.mb.api.web.model;

import jakarta.validation.constraints.NotBlank;

public class ProductModel
{
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String code;
	
	private Double unitPrice;
	
	private Integer stocks;
	
	private Integer productCategoryId;
	
	

	public ProductModel() {}

	public ProductModel(@NotBlank String name, @NotBlank String description, Double unitPrice, Integer stocks)
	{
		super();
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.stocks = stocks;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Double getUnitPrice()
	{
		return unitPrice;
	}

	public Integer getStocks()
	{
		return stocks;
	}
	public Integer getProductCategoryId()
	{
		return productCategoryId;
	}
}
