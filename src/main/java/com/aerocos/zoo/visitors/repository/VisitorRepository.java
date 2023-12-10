package com.aerocos.zoo.visitors.repository;

import com.aerocos.zoo.visitors.dto.VisitorDTO;

import java.util.List;

public interface VisitorRepository {

    VisitorDTO getVisitorById();

    List<VisitorDTO> getAllVisitors();


}
