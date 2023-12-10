package com.aerocos.zoo.db.animal.repository;

import com.aerocos.zoo.db.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalJpaRepository extends JpaRepository<Animal, Integer> {
}
