package com.mb.api.web.controller;

import static com.mb.api.business.constant.GenericConstant.CATEGORIES;
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
import com.mb.api.business.service.CategoryService;
import com.mb.api.persistance.entity.Category;
import com.mb.api.web.model.CategoryModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping(CATEGORIES)
public class CategoryController extends BaseController
{
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(@RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize)
	{

		return new ResponseEntity<>(categoryService.getAllCategory(pageSize), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryModel categoryModel)
	{
		return new ResponseEntity<>(categoryService.saveCategory(categoryModel), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategory(@RequestBody CategoryModel categoryModel, @PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(categoryService.updateCategory(categoryModel, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable(name = "id") Integer id)
	{
		return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
	}
}
