package hu.sabikov.idomsoft.assignment.microservice1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KodSzotar21Allampolg {

    private String dictname;
    private List<Row> rows;
}
