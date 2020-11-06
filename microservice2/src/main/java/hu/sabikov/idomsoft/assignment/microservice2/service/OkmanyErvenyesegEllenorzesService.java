package hu.sabikov.idomsoft.assignment.microservice2.service;

import java.util.Date;

public interface OkmanyErvenyesegEllenorzesService {

    boolean isValidFormat(String date);

    boolean isExpired(Date lejaratDatum);
}
