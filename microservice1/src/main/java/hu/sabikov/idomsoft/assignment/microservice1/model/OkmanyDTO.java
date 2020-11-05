package hu.sabikov.idomsoft.assignment.microservice1.model;

import lombok.Data;

import java.util.Date;

@Data
public class OkmanyDTO {

    private static final long serialVersionUID = 1L;

    private String okmTipus;

    private String okmanySzam;

    private byte[] okmanyKep;

    private Date lejarDat;

    private boolean ervenyes;
}
