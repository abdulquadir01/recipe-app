package com.abdulquadir.recipeapp.repositories;

import com.abdulquadir.recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
