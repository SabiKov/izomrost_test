package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import hu.sabikov.idomsoft.assignment.microservice1.model.SzemelyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

@Slf4j
@Service
public class RequestProcessStepImpl implements RequestProcessStep {


    @Autowired
    DataErrorService dataErrorService;

    @Autowired
    NameValidatorService nameValidatorService;

    @Autowired
    SzuletesiEvValidatorService szuletesiEvValidatorService;

    @Autowired
    KodSzotar21AllampolgService kodSzotar21AllampolgService;

    @Autowired
    OkmanyDtoService okmanyDtoService;

    @Override
    public ResponseEntity processWorkFlow(SzemelyDTO szemelyDTO) {
        //validate viselNev
        boolean isValid = nameValidatorService.isValid(szemelyDTO.getVisNev());
        if(!isValid)
            dataErrorService.addInfoAboutIncorrectData("hibás viselt neve");

        isValid = nameValidatorService.isValid(szemelyDTO.getSzulNev());
        if(!isValid)
            dataErrorService.addInfoAboutIncorrectData("hibás születési neve");

        isValid = nameValidatorService.isValid(szemelyDTO.getANev());
        if(!isValid)
            dataErrorService.addInfoAboutIncorrectData("hibás anyja neve");

        isValid = szuletesiEvValidatorService.isValidAge(szemelyDTO.getSzulDat());
        if(!isValid)
            dataErrorService.addInfoAboutIncorrectData("hibás születési idő");

        String allamPolgarsagDeKod = kodSzotar21AllampolgService.findAllampDekod(szemelyDTO.getAllampKod());
        log.info("allamPolgarsagDeKod found {}", allamPolgarsagDeKod);
        if(allamPolgarsagDeKod == null) {
            dataErrorService.addInfoAboutIncorrectData("hibás állampolgárság");
            dataErrorService.addInfoAboutIncorrectData("nem létező állampolgárság");
        }
        else {
            log.info("allamPolgarsagDeKod added {}", allamPolgarsagDeKod);
            szemelyDTO.setAllampKod(allamPolgarsagDeKod);
        }

        //Todo invoke second service2
        List<OkmanyDTO> okmanyDTOs = szemelyDTO.getOkmLista();

        ResponseEntity<Object> microservice2Response = okmanyDtoService
                .getOkmanyDtoResponse(okmanyDTOs);

        //Todo ellenőrizni a response valid vagy nem

        return null;
    }


}
