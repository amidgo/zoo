package com.aerocos.zoo.animals.controller;


import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;
import com.aerocos.zoo.animals.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/animals")
@RestController
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalDTO getAnimalById(@PathVariable Integer id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createAnimal(@RequestBody AnimalDataDTO animalDataDTO) {
        return animalService.createAnimal(animalDataDTO);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnimal(@PathVariable Integer id, @RequestBody AnimalDataDTO animalDataDTO) {
        animalService.updateAnimal(id, animalDataDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }

}
