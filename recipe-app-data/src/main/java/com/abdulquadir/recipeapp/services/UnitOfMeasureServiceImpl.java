package com.abdulquadir.recipeapp.services;

import com.abdulquadir.recipeapp.commands.UnitOfMeasureCommand;
import com.abdulquadir.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.abdulquadir.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
        this.uomRepository = uomRepository;
        this.uomToUomCommand = uomToUomCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

        return StreamSupport.stream(uomRepository.findAll().spliterator(), false)
                .map(uomToUomCommand::convert)
                .collect(Collectors.toSet());

    }
}
