package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.model.DataError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DataErrorServiceImpl implements DataErrorService {

    @Autowired
    DataError dataError;


    @Override
    public void addInfoAboutIncorrectData(String msg) {
        log.info("issue addInfoAboutIncorrectData(msg) {} ", msg);
        dataError.addInfoAboutIncorrectData(msg);
    }

    @Override
    public int sizeOfInfoAboutIncorrectData() {
        return dataError.sizeOfInfoAboutIncorrectData();
    }

    @Override
    public List<String> getInfoAboutIncorrectData() {
        return dataError.getInfoAboutIncorrectData();
    }
}
