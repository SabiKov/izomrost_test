package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import hu.sabikov.idomsoft.assignment.microservice1.model.SzemelyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        log.info("szemelyDTO.getVisNev() {}", szemelyDTO.getVisNev() );
        boolean isValid = nameValidatorService.isValid(szemelyDTO.getVisNev());
        if(!isValid)
            dataErrorService.addInfoAboutIncorrectData("hibás viselt neve");

        log.info("szemelyDTO.getSzulNev() {}", szemelyDTO.getSzulNev());
        isValid = nameValidatorService.isValid(szemelyDTO.getSzulNev());
        if(!isValid)
            dataErrorService.addInfoAboutIncorrectData("hibás születési neve");

        szemelyDTO.setAnyjaNeve("random muter");
        log.info("szemelyDTO.getANev() {}", szemelyDTO.getAnyjaNeve());
        isValid = nameValidatorService.isValid(szemelyDTO.getAnyjaNeve());
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

        if(microservice2Response.getStatusCodeValue() == 403) {
            log.info("hiba az microservice 2 doksiban");
            List<String> okmanyErrorList = (List<String>) microservice2Response.getBody();
            List errorLists = Stream.concat(
                    dataErrorService.getInfoAboutIncorrectData().stream(),
                    okmanyErrorList.stream())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errorLists, HttpStatus.FORBIDDEN);
        }
        //Todo ellenőrizni a response valid vagy nem

        return new ResponseEntity(HttpStatus.OK);
    }


}
