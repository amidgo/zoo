package com.aerocos.zoo.animals.repository.animal;

import com.aerocos.zoo.animals.dto.AnimalDTO;
import com.aerocos.zoo.animals.dto.AnimalDataDTO;
import com.aerocos.zoo.animals.dto.AnimalKindDTO;
import com.aerocos.zoo.animals.exception.AnimalKindNotFoundException;
import com.aerocos.zoo.animals.exception.AnimalNotFoundException;
import com.aerocos.zoo.animals.mapper.AnimalKindToAnimalKindDTOMapper;
import com.aerocos.zoo.animals.mapper.AnimalToAnimalDTOMapper;
import com.aerocos.zoo.db.animal.entity.Animal;
import com.aerocos.zoo.db.animal.repository.AnimalJpaRepository;
import com.aerocos.zoo.db.animalkind.entity.AnimalKind;
import com.aerocos.zoo.db.animalkind.repository.AnimalKindJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Random;


@DataJpaTest
class AnimalRepositoryImplTest {

    @Autowired
    private AnimalJpaRepository animalJpaRepository;

    @Autowired
    private AnimalKindJpaRepository animalKindJpaRepository;

    private final AnimalToAnimalDTOMapper animalToAnimalDTOMapper = new AnimalToAnimalDTOMapper(
            new AnimalKindToAnimalKindDTOMapper()
    );

    private AnimalRepository animalRepository;

    @BeforeEach
    void beforeEach() {
        animalRepository = new AnimalRepositoryImpl(
                animalJpaRepository,
                animalKindJpaRepository,
                animalToAnimalDTOMapper
        );
    }

    @AfterEach
    void afterEach() {
        animalJpaRepository.deleteAll();
        animalKindJpaRepository.deleteAll();
    }

    @Test
    void create_animal_with_non_existent_animal_kind() {
        AnimalDataDTO animalDataDTO = new AnimalDataDTO("cat", "empty");
        Executable createAnimalExecutable = createAnimalExecutable(animalDataDTO);
        Assertions.assertThrows(AnimalKindNotFoundException.class, createAnimalExecutable);
    }

    @Test
    void create_animal_with_existing_animal_kind() {
        String kind = "finely";
        String animalName = "cat";

        AnimalKind animalKind = createAnimalKind(kind);
        Assertions.assertNotNull(animalKind.getId());

        AnimalDTO actualAnimal = createAnimal(animalName, kind);
        Assertions.assertNotNull(actualAnimal.getId());

        Animal expectedAnimal = new Animal(actualAnimal.getId(), animalName, animalKind);
        AnimalDTO expectedAnimalDTO = animalToAnimalDTOMapper.apply(expectedAnimal);

        Assertions.assertEquals(expectedAnimalDTO, actualAnimal);
    }


    private AnimalKind createAnimalKind(String kind) {
        AnimalKind animalKind = new AnimalKind(null, kind);
        return animalKindJpaRepository.save(animalKind);
    }

    private Executable createAnimalExecutable(AnimalDataDTO animalDataDTO) {
        return () -> animalRepository.create(animalDataDTO);
    }

    @Test
    void update_non_existent_animal() {
        String kind = "finely";
        String animalName = "cat";
        AnimalDataDTO animalDataDTO = new AnimalDataDTO(animalName, kind);

        createAnimalKind(kind);

        Random random = new Random();
        Integer id = random.nextInt();
        Executable updateAnimalExecutable = updateAnimalExecutable(id, animalDataDTO);

        Assertions.assertThrows(AnimalNotFoundException.class, updateAnimalExecutable);
    }

    @Test
    void update_animal_with_non_existent_animal_kind() {
        String kind = "finely";
        String animalName = "cat";

        createAnimalKind(kind);
        AnimalDTO animalDTO = createAnimal(animalName, kind);
        Integer id = animalDTO.getId();
        Assertions.assertNotNull(id);

        AnimalDataDTO updateAnimalDataDTO = new AnimalDataDTO("name", "kind");
        Executable updateAnimalExecutable = updateAnimalExecutable(id, updateAnimalDataDTO);

        Assertions.assertThrows(AnimalKindNotFoundException.class, updateAnimalExecutable);
    }

    @Test
    void update_animal_with_existent_animal_kind() {
        String kind = "finely";
        String animalName = "cat";

        createAnimalKind(kind);
        Integer id = createAnimalAndAssertNotNullId(animalName, kind).getId();

        String updateAnimalKind = "canine";
        String updateAnimalName = "dog";

        AnimalKind animalKind = createAnimalKind(updateAnimalKind);

        AnimalDataDTO updateAnimalDataDTO = new AnimalDataDTO(updateAnimalName, updateAnimalKind);
        AnimalDTO actualAnimalDTO = animalRepository.updateById(id, updateAnimalDataDTO);

        AnimalKindDTO expectedAnimalKindDTO = new AnimalKindDTO(animalKind.getId(), updateAnimalKind);
        AnimalDTO expectedAnimalDTO = new AnimalDTO(id, updateAnimalName, expectedAnimalKindDTO);

        Assertions.assertEquals(actualAnimalDTO, expectedAnimalDTO);
    }

    private Executable updateAnimalExecutable(Integer id, AnimalDataDTO animalDataDTO) {
        return () -> animalRepository.updateById(id, animalDataDTO);
    }

    @Test
    void find_non_existent_animal() {
        Random random = new Random();
        Integer id = random.nextInt();

        Executable findByIdExecutable = findByIdExecutable(id);

        Assertions.assertThrows(AnimalNotFoundException.class, findByIdExecutable);
    }

    @Test
    void find_existent_animal() {
        String animalName = "cat";
        String kind = "finely";

        createAnimalKind(kind);
        AnimalDTO expectedAnimalDTO = createAnimalAndAssertNotNullId(animalName, kind);
        AnimalDTO actualAnimalDTO = animalRepository.findById(expectedAnimalDTO.getId());

        Assertions.assertEquals(expectedAnimalDTO, actualAnimalDTO);
    }


    private AnimalDTO createAnimalAndAssertNotNullId(String animalName, String kind) {
        AnimalDTO animalDTO = createAnimal(animalName, kind);
        Integer id = animalDTO.getId();
        Assertions.assertNotNull(id);
        return animalDTO;
    }


    private AnimalDTO createAnimal(String animalName, String animalKind) {
        AnimalDataDTO animalDataDTO = new AnimalDataDTO(animalName, animalKind);
        return animalRepository.create(animalDataDTO);
    }

    private Executable findByIdExecutable(Integer id) {
        return () -> animalRepository.findById(id);
    }
}