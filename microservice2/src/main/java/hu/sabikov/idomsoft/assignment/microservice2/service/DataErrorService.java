package hu.sabikov.idomsoft.assignment.microservice2.service;

import java.util.ArrayList;
import java.util.List;

public interface DataErrorService {

    void addInfoAboutIncorrectData(String msg);

    int sizeOfInfoAboutIncorrectData();

    ArrayList<String> getInfoAboutIncorrectData();
}
