package com.aerocos.zoo.animals.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@XmlRootElement(name = "animal-data")
@AllArgsConstructor
public class AnimalDataDTO {
    private String name;
    @JsonProperty("animal_kind")
    private String animalKind;
}
