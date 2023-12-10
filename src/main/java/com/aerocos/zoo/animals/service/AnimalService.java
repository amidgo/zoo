package com.aerocos.zoo.animals.service;


import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;

import java.util.List;

public interface AnimalService {
    AnimalDTO getAnimalById(Integer animalId);
    Integer createAnimal(AnimalDataDTO animalDataDTO);
    void updateAnimal(Integer id, AnimalDataDTO animalDataDTO);

    List<AnimalDTO> getAllAnimals();
}
