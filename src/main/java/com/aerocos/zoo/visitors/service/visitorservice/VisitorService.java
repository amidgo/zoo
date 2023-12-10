package com.aerocos.zoo.visitors.service.visitorservice;

import com.aerocos.zoo.visitors.dto.VisitorDTO;
import com.aerocos.zoo.visitors.dto.VisitorDataDTO;

import java.util.List;

public interface VisitorService {

    Integer createVisitor(VisitorDataDTO visitorDataDTO);

    VisitorDTO getVisitorById(Integer id);

    List<VisitorDTO> getAllVisitors();

    VisitorDTO updateVisitorById(Integer id, VisitorDataDTO visitorDataDTO);
}
