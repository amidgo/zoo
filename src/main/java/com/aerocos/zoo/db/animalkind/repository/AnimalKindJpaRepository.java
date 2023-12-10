package com.aerocos.zoo.db.animalkind.repository;

import com.aerocos.zoo.db.animalkind.entity.AnimalKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalKindJpaRepository extends JpaRepository<AnimalKind, Integer> {

    @Query("select a from AnimalKind a where a.kind = ?1")
    Optional<AnimalKind> findByKind(String kind);
}
