package com.mb.api.business.service;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mb.api.business.exception.CustomException;
import com.mb.api.persistance.entity.Category;
import com.mb.api.persistance.repository.CategoryRepository;
import com.mb.api.web.model.CategoryModel;

@Service
public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(This.class);

	@Override
	public List<Category> getAllCategory(Integer pageSize)
	{
		LOGGER.info("------------------ Fetching Category's data-----------------------------");
		Pageable pageable = PageRequest.ofSize(pageSize);

		Page<Category> pageResult = categoryRepository.findAll(pageable);

		if (!pageResult.hasContent())
		{
			throw new CustomException("No Data Found !");
		}

		LOGGER.info("------------------ Data fetched succsfully !-----------------------------");

		return pageResult.getContent();
		
	}

	@Override
	public Object saveCategory(CategoryModel categoryModel)
	{
		LOGGER.info("------------------ Creating new category-----------------------------");

		Optional<Category> category = categoryRepository.findByCategoryName(categoryModel.getCategoryName());

		if (category.isPresent())
		{
			throw new CustomException("This Category Already exists !");
		}
		categoryRepository.save(modelMapper.map(categoryModel, Category.class));
		
		LOGGER.info("------------------ new Category created successfully !-----------------------------");
		return "Category Saved Successfully!";
	}

	@Override
	public Category getCategoryById(Integer id)
	{
		
		LOGGER.info("------------------ Category by id fetching -----------------------------");

		Optional<Category> category = categoryRepository.findById(id);

		if (category.isEmpty())
		{
			throw new CustomException("No such data found !");
		}
		
		LOGGER.info("------------------ Category by id fetched -----------------------------");
		return category.get();
	}

	@Override
	public Object updateCategory(CategoryModel categoryModel, Integer id)
	{
		LOGGER.info("------------------Update category -----------------------------");
		
		Category category = getCategoryById(id);
		category.setCategoryName(categoryModel.getCategoryName());
		categoryRepository.save(category);
		LOGGER.info("------------------Updated category -----------------------------");

		return "Category updated with ID :: " + id;
	}

	@Override
	public Object deleteCategory(Integer id)
	{	
		LOGGER.info("------------------delete category -----------------------------");

		Category category = getCategoryById(id);
		 
		if(category == null) {
			throw new CustomException("No Data found !");
		}
		
		try
		{
			categoryRepository.deleteById(id);
		}
		catch (Exception e)
		{
		 throw new CustomException("Internal Server Error !!!");
		}
		LOGGER.info("------------------deleted category -----------------------------");

		return "Category of ID ::" + id +" deleted succesfully ! ";
	}

}
