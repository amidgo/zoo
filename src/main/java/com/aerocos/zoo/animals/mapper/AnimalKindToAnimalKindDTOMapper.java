package com.aerocos.zoo.animals.mapper;


import com.aerocos.zoo.animals.dto.AnimalKindDTO;
import com.aerocos.zoo.db.animalkind.entity.AnimalKind;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AnimalKindToAnimalKindDTOMapper implements Function<AnimalKind, AnimalKindDTO> {
    @Override
    public AnimalKindDTO apply(AnimalKind animalKind) {
        return new AnimalKindDTO(animalKind.getId(), animalKind.getKind());
    }
}
