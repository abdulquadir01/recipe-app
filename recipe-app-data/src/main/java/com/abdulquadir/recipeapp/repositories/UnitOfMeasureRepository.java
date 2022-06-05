package com.abdulquadir.recipeapp.repositories;

import com.abdulquadir.recipeapp.model.Category;
import com.abdulquadir.recipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;




public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
