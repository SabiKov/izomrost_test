package hu.sabikov.idomsoft.assignment.microservice2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OkmanySzamValidatorServiceImpl implements OkmanySzamValidatorService {

    @Override
    public boolean isValid(String okmanyTipus, String okmanySzam) {
        boolean isValidDocet = false;
        switch(okmanyTipus) {
            case "1":
                isValidDocet = isValidSzig(okmanySzam);
                break;
            case "2":
                isValidDocet = isValidUtl(okmanySzam);
                break;
            case "3":
                isValidDocet = isValidVen(okmanySzam);
                break;
            default:
             log.info("rest of the docet type validation rule wasn't in the specification!");
        }
        return isValidDocet;
    }

    @Override
    public boolean isValidSzig(String okmanySzam) {
        return  okmanySzam != null &&
                okmanySzam.length() == 8 &&
                okmanySzam.substring(0, 6).matches("\\d{6}") &&
                okmanySzam.substring(6).matches("[A-Z]{2}");
    }

    @Override
    public boolean isValidUtl(String okmanySzam) {
        return  okmanySzam != null &&
                okmanySzam.length() == 9 &&
                okmanySzam.substring(0, 2).matches("[A-Z]{2}") &&
                okmanySzam.substring(2).matches("\\d{7}");
    }

    @Override
    public boolean isValidVen(String okmanySzam) {
        return  okmanySzam != null &&
                okmanySzam.matches("\\d{10}");
    }
}
