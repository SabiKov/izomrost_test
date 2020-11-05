package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice1.model.SzemelyDTO;
import org.springframework.http.ResponseEntity;

public interface RequestProcessStep {

    ResponseEntity processWorkFlow(SzemelyDTO szemelyDTO) throws InvalidRequestedParamsException;
}
