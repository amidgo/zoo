package com.aerocos.zoo.animals.controller;


import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;
import com.aerocos.zoo.animals.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/animals/xml")
@RestController
@RequiredArgsConstructor
public class AnimalXmlController {

    private final AnimalService animalService;

    @GetMapping(value = "/{id}", produces = "application/xml")
    @ResponseStatus(HttpStatus.OK)
    public AnimalDTO getAnimalById(@PathVariable Integer id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping(produces = "application/xml")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }

}
