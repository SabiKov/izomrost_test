package hu.sabikov.idomsoft.assignment.microservice2.service;

import hu.sabikov.idomsoft.assignment.microservice2.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice2.model.OkmanyDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface RequestProcessStepService {

    ResponseEntity<ArrayList> processWorkFlow(ArrayList<OkmanyDTO> okmanyDTOs) throws InvalidRequestedParamsException;
}
