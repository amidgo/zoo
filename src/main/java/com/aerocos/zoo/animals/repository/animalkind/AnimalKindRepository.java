package com.aerocos.zoo.animals.repository.animalkind;

import com.aerocos.zoo.animals.dto.AnimalKindDTO;

import java.util.List;

public interface AnimalKindRepository {
    AnimalKindDTO createAnimalKind(String animalKind);

    List<AnimalKindDTO> findAll();
}
