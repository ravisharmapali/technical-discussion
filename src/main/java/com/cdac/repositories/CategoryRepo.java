package com.cdac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
 
}
