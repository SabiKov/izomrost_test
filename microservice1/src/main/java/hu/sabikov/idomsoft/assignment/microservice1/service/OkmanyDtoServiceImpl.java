package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.config.RestTemplateConfig;
import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OkmanyDtoServiceImpl implements OkmanyDtoService {

    @Autowired
    RestTemplateConfig restTemplate;

    private List<OkmanyDTO> okmányType1;
    private List<OkmanyDTO> okmányType2;
    private List<OkmanyDTO> okmányType3;
    private List<OkmanyDTO> okmányType4;
    private List<OkmanyDTO> okmányType5;
    private List<OkmanyDTO> okmányType6;

    {
        okmányType1 = new ArrayList<>();
        okmányType2 = new ArrayList<>();
        okmányType3 = new ArrayList<>();
        okmányType4 = new ArrayList<>();
        okmányType5 = new ArrayList<>();
        okmányType6 = new ArrayList<>();
    }

    @Override
    public ResponseEntity<Object> getOkmanyDtoResponse(List<OkmanyDTO> okmanyDTO) {

        final String uri = "http://localhost:8002/mcrsrv2/okmanydto";

        Object result = restTemplate
                .restTemplate().postForObject(uri, okmanyDTO, String.class);

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}
