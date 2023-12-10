package com.aerocos.zoo.animals.service;

import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;
import com.aerocos.zoo.animals.repository.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    @Override
    public AnimalDTO getAnimalById(Integer animalId) {
        return animalRepository.findById(animalId);
    }

    @Override
    public Integer createAnimal(AnimalDataDTO animalDataDTO) {
        AnimalDTO animalDTO = animalRepository.create(animalDataDTO);
        return animalDTO.getId();
    }

    @Override
    public void updateAnimal(Integer id, AnimalDataDTO animalDataDTO) {
        animalRepository.updateById(id, animalDataDTO);
    }

    @Override
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.getAll();
    }

}
