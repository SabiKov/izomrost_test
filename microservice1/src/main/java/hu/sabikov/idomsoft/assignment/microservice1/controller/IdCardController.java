package hu.sabikov.idomsoft.assignment.microservice1.controller;

import hu.sabikov.idomsoft.assignment.microservice1.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice1.model.KodSzotar21Allampolg;
import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import hu.sabikov.idomsoft.assignment.microservice1.model.Row;
import hu.sabikov.idomsoft.assignment.microservice1.model.SzemelyDTO;
import hu.sabikov.idomsoft.assignment.microservice1.service.DataErrorService;
import hu.sabikov.idomsoft.assignment.microservice1.service.KodSzotar21AllampolgServiceImpl;
import hu.sabikov.idomsoft.assignment.microservice1.service.OkmanyDtoService;
import hu.sabikov.idomsoft.assignment.microservice1.service.RequestProcessStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mcrsrv1")
public class IdCardController {

    @Autowired
    RequestProcessStep requestProcessStep;

//    @Autowired
//    KodSzotar21AllampolgServiceImpl kodSzotar21AllampolgService;
//
//    @Autowired
//    OkmanyDtoService okmanyDtoService;
//
    @Autowired
    DataErrorService dataErrorService;

    @PostMapping(value= "/szemelydto", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processRequestedData(
            @RequestBody SzemelyDTO szemelyDTO) {

        log.info("szemelyDTO {}", szemelyDTO.toString());

        ResponseEntity<Object> response = null;

        try {
            response = requestProcessStep.processWorkFlow(szemelyDTO);
        } catch (InvalidRequestedParamsException e) {
            response = new ResponseEntity<>(dataErrorService.getInfoAboutIncorrectData(), HttpStatus.FORBIDDEN);
            e.printStackTrace();

        }

        return response;
    }

}
