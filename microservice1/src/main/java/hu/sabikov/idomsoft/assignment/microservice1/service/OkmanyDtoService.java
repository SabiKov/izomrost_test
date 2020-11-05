package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OkmanyDtoService {

    ResponseEntity<Object> getOkmanyDtoResponse(List<OkmanyDTO> okmanyDTO);
}
