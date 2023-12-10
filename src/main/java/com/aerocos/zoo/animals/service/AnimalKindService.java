package com.aerocos.zoo.animals.service;


import com.aerocos.zoo.db.animalkind.entity.AnimalKind;

public interface AnimalKindService {
    Integer createAnimalKind(String kind);

    AnimalKind getAnimalKindById(Integer id);

    void updateAnimalKindById(Integer id, String updateKind);
}
