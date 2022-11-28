package com.mb.api.business.service;

import java.util.List;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.ProductModel;

public interface ProductService
{
	List<Product> getAllProducts(Integer page);
	
	Object saveProduct(ProductModel productModel);
	
	Product getProductById(Integer id);
	
	Object updateProduct(ProductModel productModel, Integer id);
	
	Object deleteProduct(Integer id);
	

}
