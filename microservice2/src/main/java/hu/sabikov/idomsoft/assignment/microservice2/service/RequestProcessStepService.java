package hu.sabikov.idomsoft.assignment.microservice2.service;

import hu.sabikov.idomsoft.assignment.microservice2.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice2.model.OkmanyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RequestProcessStepService {

    ResponseEntity<List> processWorkFlow(List<OkmanyDTO> okmanyDTOs) throws InvalidRequestedParamsException;
}
