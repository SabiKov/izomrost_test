package hu.sabikov.idomsoft.assignment.microservice2.service;

public interface OkmanySzamValidatorService {

    boolean isValid(String okmanyTipus, String okmanySzam);

    boolean isValidSzig( String okmanySzam);
    boolean isValidUtl( String okmanySzam);
    boolean isValidVen( String okmanySzam);
}
