package hu.sabikov.idomsoft.assignment.microservice1.service;

import java.util.Date;

public interface SzuletesiEvValidatorService {

    boolean isValidFormat(String dateStr);

    boolean isAdult(int age);

    boolean isAlive(int age);

    boolean isAgeInRange(int age);

    int calculateAge(Date date);

    boolean isErvenyesSzuletesiEv(Date date);

    boolean isValidAge(Date date);
}
