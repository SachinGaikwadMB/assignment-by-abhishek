package com.mb.api.business.service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mb.api.business.exception.CustomException;
import com.mb.api.persistance.entity.Product;
import com.mb.api.persistance.repository.CategoryRepository;
import com.mb.api.persistance.repository.ProductRepository;
import com.mb.api.web.model.ProductModel;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(This.class);

	@Override
	public List<Product> getAllProducts(Integer page)
	{
		LOGGER.info("-------------------- Fetching All Products------------------");
		Pageable pageable = PageRequest.ofSize(page);

		Page<Product> pageResult = productRepository.findAll(pageable);

		if (!pageResult.hasContent())
		{
			throw new CustomException("No Data found !!!");
		}

		LOGGER.info("-------------------- Fetched All Products------------------");
		return pageResult.getContent();
	}

	@Override
	public Object saveProduct(ProductModel productModel)
	{
		LOGGER.info("-------------------- Saving single Product------------------");

		Product product = new Product();

		product.setName(productModel.getName());
		product.setCode(productModel.getCode());
		product.setDescription(productModel.getDescription());
		product.setUnitPrice(productModel.getUnitPrice());
		product.setStocks(productModel.getStocks());
		product.setCategory(categoryRepository.findById(productModel.getProductCategoryId()).get());

		productRepository.save(product);

		LOGGER.info("-------------------- Saved Single Product------------------");
		return "Product Saved Successfully !!!";
	}

	@Override
	public Product getProductById(Integer id)
	{
		LOGGER.info("-------------------- Fetching single Product ------------------");
		Optional<Product> product = productRepository.findById(id);

		if (product.isEmpty())
		{
			throw new CustomException("No such product found !!!");
		}
		LOGGER.info("-------------------- Fetching single Product------------------");
		return product.get();
	}

	@Override
	public Object updateProduct(ProductModel productModel, Integer id)
	{
		LOGGER.info("-------------------- Update Product------------------");

		Product product = getProductById(id);
		product.setName(productModel.getName());
		product.setDescription(productModel.getDescription());
		product.setStocks(productModel.getStocks());
		product.setUnitPrice(productModel.getUnitPrice());
		product.setCode(productModel.getCode());

		productRepository.save(product);
		LOGGER.info("-------------------- Updated Product------------------");
		return "product updated with ID :: " + id;

	}

	@Override
	public Object deleteProduct(Integer id)
	{
		LOGGER.info("-------------------- Deleting Product------------------");
		Product product = getProductById(id);

		if (product == null)
		{
			throw new CustomException("No Data found !");
		}

		productRepository.deleteById(id);
		LOGGER.info("-------------------- Deleted Product------------------");

		return "Product of ID ::" + id + " deleted succesfully ! ";

	}

}
