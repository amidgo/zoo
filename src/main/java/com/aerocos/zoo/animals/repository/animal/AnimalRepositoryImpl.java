package com.aerocos.zoo.animals.repository.animal;

import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;
import com.aerocos.zoo.animals.exception.AnimalKindNotFoundException;
import com.aerocos.zoo.animals.exception.AnimalNotFoundException;
import com.aerocos.zoo.animals.mapper.AnimalToAnimalDTOMapper;
import com.aerocos.zoo.db.animal.entity.Animal;
import com.aerocos.zoo.db.animal.entity.AnimalConstraints;
import com.aerocos.zoo.db.animal.repository.AnimalJpaRepository;
import com.aerocos.zoo.db.animalkind.entity.AnimalKind;
import com.aerocos.zoo.db.animalkind.repository.AnimalKindJpaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepository {

    private final AnimalJpaRepository animalJpaRepository;
    private final AnimalKindJpaRepository animalKindJpaRepository;

    private final AnimalToAnimalDTOMapper animalToAnimalDTOMapper;

    @Override
    public AnimalDTO create(AnimalDataDTO animalDataDTO) {
        Animal animalFromData = animalFromData(animalDataDTO);
        Animal savedAnimal = catchSaveException(animalFromData);
        return animalToAnimalDTOMapper.apply(savedAnimal);
    }

    private Animal animalFromData(AnimalDataDTO animalDataDTO) {
        Animal animal = new Animal();
        animal.setName(animalDataDTO.getName());
        AnimalKind animalKind = findByKindOrElseThrowNotFoundException(animalDataDTO.getAnimalKind());
        animal.setAnimalKind(animalKind);
        return animal;
    }


    @Override
    public AnimalDTO updateById(Integer id, AnimalDataDTO animalDataDTO){
        Animal animal = findByIdOrElseThrowNotFoundException(id);
        animal = updateAnimal(animal, animalDataDTO);
        return animalToAnimalDTOMapper.apply(animal);
    }

    private Animal updateAnimal(Animal current,AnimalDataDTO animalDataDTO) {
        AnimalKind animalKind = getUpdateAnimalKind(current.getAnimalKind(), animalDataDTO.getAnimalKind());
        current.setAnimalKind(animalKind);
        current.setName(animalDataDTO.getName());
        return catchSaveException(current);
    }


    private AnimalKind getUpdateAnimalKind(AnimalKind current, String updateKind){
        if (current.getKind().equals(updateKind))
            return current;
        return findByKindOrElseThrowNotFoundException(updateKind);
    }

    private AnimalKind findByKindOrElseThrowNotFoundException(String kind)
            throws AnimalKindNotFoundException {
        Optional<AnimalKind> animalKindOptional = animalKindJpaRepository.findByKind(kind);
        return animalKindOptional.orElseThrow(AnimalKindNotFoundException::new);
    }


    private Animal catchSaveException(Animal animal) {
        try{
            return animalJpaRepository.save(animal);
        }catch (ConstraintViolationException constraintViolationException) {
            String constraintName = constraintViolationException.getConstraintName();
            if (constraintName.equals(AnimalConstraints.FOREIGN_KEY_ANIMAL_KINDS)) {
                throw new AnimalKindNotFoundException();
            }
            throw constraintViolationException;
        }
    }

    @Override
    public AnimalDTO findById(Integer id) {
        Animal animal = findByIdOrElseThrowNotFoundException(id);
        return animalToAnimalDTOMapper.apply(animal);
    }

    @Override
    public List<AnimalDTO> getAll() {
        Sort sortAscendingAnimalName = Sort.by(Sort.Direction.ASC, "name");
        return animalJpaRepository
                .findAll(sortAscendingAnimalName)
                .stream()
                .map(animalToAnimalDTOMapper)
                .toList();
    }


    private Animal findByIdOrElseThrowNotFoundException(Integer id) throws AnimalNotFoundException {
        Optional<Animal> animalOptional = animalJpaRepository.findById(id);
        return animalOptional.orElseThrow(AnimalNotFoundException::new);
    }
}
