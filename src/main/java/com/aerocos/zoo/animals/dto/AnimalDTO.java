package com.aerocos.zoo.animals.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@XmlRootElement(name = "animal")
@AllArgsConstructor
public class AnimalDTO {
    private Integer id;
    private String name;
    @JsonProperty("animal_kind")
    private AnimalKindDTO animalKind;
}
