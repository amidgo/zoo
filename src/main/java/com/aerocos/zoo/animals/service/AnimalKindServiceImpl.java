package com.aerocos.zoo.animals.service;

import com.aerocos.zoo.db.animalkind.entity.AnimalKind;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AnimalKindServiceImpl implements AnimalKindService{
    @Override
    public Integer createAnimalKind(String kind) {
        return null;
    }

    @Override
    public AnimalKind getAnimalKindById(Integer id) {
        return null;
    }

    @Override
    public void updateAnimalKindById(Integer id, String updateKind) {

    }


}
