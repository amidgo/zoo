package com.aerocos.zoo.animals.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@XmlRootElement(name = "animal-kind")
@AllArgsConstructor
public class AnimalKindDTO {
    private Integer id;
    private String kind;
}
