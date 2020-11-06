package hu.sabikov.idomsoft.assignment.microservice1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NameValidatorServiceImpl implements NameValidatorService {

    private static final String availableChars =
            "ÄAÁBCDEFGHIÍJKLMNOÓÖŐPQRSTYZWVaábcdefghiíjklmnoóöőpqrstyzwv./'- ";

    private final int MAX_LENGTH = 80;

    @Override
    public boolean isContainsBothNames(String name) {
        String tempName = name == null ? "" : name;
        log.info("name: {}", name);
        log.info("name.contains DR. {}", name.contains("Dr."));
        boolean isDrExist = name.contains("Dr.");

        if(isDrExist) {
            tempName.replace("Dr.", "");
        }
        String[] names = tempName.split(" ");
        return names.length > 1 && names[0].length() != 0 && names[1].length() !=0;
    }

    @Override
    public boolean isContainUnwantedChar(String name) {
        for(int i = 0; i < name.length(); i++) {
            int result = availableChars.indexOf(name.charAt(i));
            if(result == -1)
                return true;
        }
        return false;
    }

    @Override
    public boolean isTooLengthy(String name) {
        log.info("name {}", name);
        log.info("name.length() {} ", name.length());
        return name == null || name.length() > MAX_LENGTH;
    }

    public boolean isValid(String str) {
        log.info("48 isValid str {}", str);
        return isContainsBothNames(str) &&
                !isContainUnwantedChar(str) &&
                !isTooLengthy(str);
    }
}
