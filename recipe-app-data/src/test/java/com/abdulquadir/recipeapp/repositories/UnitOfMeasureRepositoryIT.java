//package com.abdulquadir.recipeapp.repositories;
//
//import com.abdulquadir.recipeapp.model.UnitOfMeasure;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UnitOfMeasureRepositoryIT {
//
//    @Autowired
//    UnitOfMeasureRepository unitOfMeasureRepository;
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @Test
//    public void findByDescription() {
//        Optional<UnitOfMeasure> optionalUOM = unitOfMeasureRepository.findByDescription("Teaspoon");
//        assertEquals("Teaspoon", optionalUOM.get().getDescription());
//    }
//}