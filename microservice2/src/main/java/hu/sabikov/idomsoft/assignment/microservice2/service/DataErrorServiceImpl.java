package hu.sabikov.idomsoft.assignment.microservice2.service;

import hu.sabikov.idomsoft.assignment.microservice2.model.DataError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataErrorServiceImpl implements DataErrorService {

    @Autowired
    DataError dataError;


    @Override
    public void addInfoAboutIncorrectData(String msg) {
        dataError.addInfoAboutIncorrectData(msg);
    }

    @Override
    public int sizeOfInfoAboutIncorrectData() {
        return dataError.sizeOfInfoAboutIncorrectData();
    }

    @Override
    public ArrayList<String> getInfoAboutIncorrectData() {
        return dataError.getInfoAboutIncorrectData();
    }
}
