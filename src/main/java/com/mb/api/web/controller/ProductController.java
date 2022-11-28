package com.mb.api.web.controller;

import static com.mb.api.business.constant.GenericConstant.PRODUCTS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.service.ProductService;
import com.mb.api.persistance.entity.Product;
import com.mb.api.web.model.ProductModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping(PRODUCTS)
public class ProductController extends BaseController
{
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "page", required = false, defaultValue = "3") Integer page) {
		return new ResponseEntity<>(productService.getAllProducts(page), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductModel productModel) {
		return new ResponseEntity<>(productService.saveProduct(productModel), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Integer id) {
		return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody @Valid ProductModel productModel, @PathVariable(name = "id")Integer id) {
		return new ResponseEntity<>(productService.updateProduct(productModel, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
	}

}
