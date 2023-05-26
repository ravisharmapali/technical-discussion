package com.cdac.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entities.Category;
import com.cdac.exceptions.ResourceNotFoundException;
import com.cdac.payloads.CategoryDto;
import com.cdac.repositories.CategoryRepo;
import com.cdac.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// 
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		Category addedCategory=	this.categoryRepo.save(category);
		
		CategoryDto dto = this.modelMapper.map(addedCategory, CategoryDto.class);
		return dto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// 
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", 
				"id", categoryId));
		
		//updating category object
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
				
		//save to db
		Category savedCategory = this.categoryRepo.save(category);

		
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", " id ", categoryId));
		
		this.categoryRepo.delete(category);
				
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", " id ", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> allCategory = this.categoryRepo.findAll();
		
		List<CategoryDto> list = allCategory.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return list;
	}
 
}
 