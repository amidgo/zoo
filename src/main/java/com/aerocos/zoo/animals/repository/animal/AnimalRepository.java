package com.aerocos.zoo.animals.repository.animal;

import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;

import java.util.List;

public interface AnimalRepository {
    AnimalDTO create(AnimalDataDTO animalDataDTO);

    AnimalDTO updateById(Integer id, AnimalDataDTO animalDataDTO);

    AnimalDTO findById(Integer id);

    List<AnimalDTO> getAll();
}
