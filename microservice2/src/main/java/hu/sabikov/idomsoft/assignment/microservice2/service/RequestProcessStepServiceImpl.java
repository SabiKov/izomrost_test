package hu.sabikov.idomsoft.assignment.microservice2.service;

import hu.sabikov.idomsoft.assignment.microservice2.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice2.model.OkmanyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RequestProcessStepServiceImpl implements RequestProcessStepService {

    @Autowired
    OkmanySzamValidatorService okmanySzamValidatorService;

    @Autowired
    OkmanyDataStructure okmanyDataStructure;

    @Autowired
    DataErrorService dataErrorService;

    @Override
    public ResponseEntity<List> processWorkFlow(List<OkmanyDTO> okmanyDTOs) throws InvalidRequestedParamsException {
        //Todo suppose only one docet can be, then must be able to recieve multiple docets type
        log.info("size of okmany {}", okmanyDTOs.size());

        //Sorting based on docet type
        for(OkmanyDTO okmanyDTO : okmanyDTOs) {
            okmanyDataStructure.addOkmanyDtoToList(okmanyDTO);
        }

        boolean isMultipleSameValidDocet = okmanyDataStructure.isMultipleValidOkmany();
        if(isMultipleSameValidDocet) {
            dataErrorService.addInfoAboutIncorrectData("több érvényes dokument tipus");
            return new ResponseEntity<>(
                    dataErrorService.getInfoAboutIncorrectData(),
                    HttpStatus.FORBIDDEN);
        }

        List<OkmanyDTO> remergedList = okmanyDataStructure.mergeIntoSingleList();
        List<OkmanyDTO> listWithCheckingExpiration = new ArrayList<>();
        for(OkmanyDTO okmanyDTO : remergedList) {
            boolean isValidOkmanySzam =
                    okmanySzamValidatorService.isValid(
                            okmanyDTO.getOkmTipus(),
                            okmanyDTO.getOkmanySzam());
            if(isValidOkmanySzam) {
                dataErrorService.addInfoAboutIncorrectData("hibás okmányszám " + okmanyDTO.getOkmanySzam());
            }
            boolean isValidExipration = !okmanySzamValidatorService.isValid(
                    okmanyDTO.getOkmTipus(),
                    okmanyDTO.getOkmanySzam());
            okmanyDTO.setErvenyes(isValidExipration);
            listWithCheckingExpiration.add(okmanyDTO);
        }
        if(dataErrorService.sizeOfInfoAboutIncorrectData() > 0) {
            return new ResponseEntity<>(
                    dataErrorService.getInfoAboutIncorrectData(),
                    HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(listWithCheckingExpiration, HttpStatus.OK);
    }
}
