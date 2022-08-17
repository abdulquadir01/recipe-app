package com.abdulquadir.recipeapp.services;

import com.abdulquadir.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;




public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
