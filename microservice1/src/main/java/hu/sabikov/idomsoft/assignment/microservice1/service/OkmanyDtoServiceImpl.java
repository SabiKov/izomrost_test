package hu.sabikov.idomsoft.assignment.microservice1.service;

import hu.sabikov.idomsoft.assignment.microservice1.config.RestTemplateConfig;
import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OkmanyDtoServiceImpl implements OkmanyDtoService {

    @Autowired
    RestTemplateConfig restTemplate;

    @Override
    public ResponseEntity<List<OkmanyDTO>> getOkmanyDtoResponse(ArrayList
            <OkmanyDTO> okmanyDTOs) {

        log.info("OkmanyDtoServiceImpl {}", okmanyDTOs);
        final String uri = "http://localhost:8002/mcrsrv2/okmanydto";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<Object> requestEntity = new HttpEntity<>(okmanyDTOs, headers);

        ArrayList<OkmanyDTO> result = null;
        try {
            ResponseEntity<List<OkmanyDTO>> okmanyDtoResponse = restTemplate
                    .restTemplate().exchange(
                            uri,
                            HttpMethod.POST,
                            requestEntity,
                            new ParameterizedTypeReference<List<OkmanyDTO>>() {
                            });
            if (okmanyDtoResponse != null && okmanyDtoResponse.hasBody()) {
                result = (ArrayList<OkmanyDTO>) okmanyDtoResponse.getBody();
            }
        } catch  (RestClientException e) {
            log.info("RestClientException {} ", e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
