package com.aerocos.zoo.animals.mapper;


import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalKindDTO;
import com.aerocos.zoo.db.animal.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class AnimalToAnimalDTOMapper implements Function<Animal, AnimalDTO> {
    private final AnimalKindToAnimalKindDTOMapper animalKindToAnimalKindDTOMapper;

    @Override
    public AnimalDTO apply(Animal animal) {
        AnimalKindDTO animalKindDTO = animalKindToAnimalKindDTOMapper.apply(animal.getAnimalKind());
        return new AnimalDTO(animal.getId(), animal.getName(), animalKindDTO);
    }
}
