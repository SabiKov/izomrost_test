package hu.sabikov.idomsoft.assignment.microservice2.service;

import hu.sabikov.idomsoft.assignment.microservice2.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice2.model.OkmanyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RequestProcessStepServiceImpl implements RequestProcessStepService {

    @Autowired
    OkmanySzamValidatorService okmanySzamValidatorService;
    @Autowired
    OkmanyErvenyesegEllenorzesService okmanyErvenyesegEllenorzesService;

    @Override
    public ResponseEntity<List> processWorkFlow(List<OkmanyDTO> okmanyDTOs) throws InvalidRequestedParamsException {
        //Todo suppose only one docet can be, then must be able to recieve multiple docets type
        log.info("size of okmany {}", okmanyDTOs.size());
        OkmanyDTO okmanyDTO = okmanyDTOs.get(0);
        boolean isValid = okmanySzamValidatorService.isValid(
                okmanyDTO.getOkmTipus(),
                okmanyDTO.getOkmanySzam());
        boolean isOkmanyErvenyes = okmanyErvenyesegEllenorzesService.isValid(okmanyDTO.getLejarDat());
        okmanyDTO.setErvenyes(isOkmanyErvenyes);
        return null;
    }
}
