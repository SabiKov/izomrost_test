package hu.sabikov.idomsoft.assignment.microservice1.service;

public interface NameValidatorService {

    boolean isContainsBothNames(String name);

    boolean isContainUnwantedChar(String name);

    boolean isTooLengthy(String name);

    boolean isValid(String str);

}
