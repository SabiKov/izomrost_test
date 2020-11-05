package hu.sabikov.idomsoft.assignment.microservice2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KodSzotar46OkmanyTipus {

    private String dictname;
    private List<Row> rows;

}
