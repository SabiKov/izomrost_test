package hu.sabikov.idomsoft.assignment.microservice2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OkmanyDTO {

    private static final long serialVersionUID = 1L;

    private String okmTipus;

    private String okmanySzam;

    private byte[] okmanyKep;

    private Date lejarDat;

    private boolean ervenyes;
}
