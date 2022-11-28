package com.mb.api.business.service;

import java.util.List;
import com.mb.api.persistance.entity.Category;
import com.mb.api.web.model.CategoryModel;

public interface CategoryService
{
	//GET :: all the categories
	List<Category> getAllCategory(Integer pageSize);
	
	//POST :: create new category 
	Object saveCategory(CategoryModel categoryModel);
	
	//GET :: get category by id
	Category getCategoryById(Integer id);
	
	//PUT :: Update by id
	Object updateCategory(CategoryModel categoryModel, Integer id);
	
	//DELETE :: delete category
	
	Object deleteCategory(Integer id);
}
