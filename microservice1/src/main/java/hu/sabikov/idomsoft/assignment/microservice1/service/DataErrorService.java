package hu.sabikov.idomsoft.assignment.microservice1.service;

import java.util.List;

public interface DataErrorService {

    void addInfoAboutIncorrectData(String msg);

    int sizeOfInfoAboutIncorrectData();

    List<String> getInfoAboutIncorrectData();
}
