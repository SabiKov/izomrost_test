package hu.sabikov.idomsoft.assignment.microservice1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Row {

    private String kod;
    private String allampolgarsag;
    private String orszag;
    private String schengen;
    private String sis1_orsz;
    private String sis1_orsz_hun;
}
