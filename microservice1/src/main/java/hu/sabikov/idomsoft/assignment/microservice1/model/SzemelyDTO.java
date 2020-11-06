package hu.sabikov.idomsoft.assignment.microservice1.model;


import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Date;

@Data
@Component
public class SzemelyDTO {

    private static final long serialVersionUID = 4L;

    private String visNev;

    private String szulNev;

    private String anyjaNeve;

    private Date szulDat;

    private String neme;

    private String allampKod;

    private String allampDekod;

    private ArrayList<OkmanyDTO> okmLista;
}
