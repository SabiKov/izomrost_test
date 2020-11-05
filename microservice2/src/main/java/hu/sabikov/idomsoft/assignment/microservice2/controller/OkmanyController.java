package hu.sabikov.idomsoft.assignment.microservice2.controller;


import hu.sabikov.idomsoft.assignment.microservice2.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice2.model.OkmanyDTO;
import hu.sabikov.idomsoft.assignment.microservice2.service.Kodszotar46OkmanyTipusService;
import hu.sabikov.idomsoft.assignment.microservice2.service.RequestProcessStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mcrsrv2")
public class OkmanyController {

    @Autowired
    RequestProcessStepService requestProcessStepService;

    @PostMapping("okmanydto")
    public ResponseEntity<List> processRequestedData(
            @RequestBody List<OkmanyDTO> okmanyDTOs) {

        ResponseEntity<List> response = null;
        try {
            response = requestProcessStepService.processWorkFlow(okmanyDTOs);
        } catch (InvalidRequestedParamsException e) {
            e.printStackTrace();
        }

//        List<String> hiba = new ArrayList<>();
//        hiba.add("hiba okmTipus");
//        hiba.add("hiba okmTipus");
//        hiba.add("hiba okmanyKep");
//        hiba.add("hiba lejarDat");
//        hiba.add("hiba ervenyesseg");
//
//        OkmanyDTO okmanyDTO1 = new OkmanyDTO();
//        okmanyDTO1.setOkmTipus("1");
//        okmanyDTO1.setOkmanySzam("1234565HJ");
//        byte[] kep = {1, 3, 5, 7};
//        okmanyDTO1.setOkmanyKep(kep);
//        okmanyDTO1.setErvenyes(false);
//
//        String expired = "2020-03-11";
//        SimpleDateFormat formatterExpired = new SimpleDateFormat("yyyy-MM-dd");
//        Date docExpired = null;
//        try {
//            docExpired = formatterExpired.parse(expired);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        okmanyDTO1.setLejarDat(docExpired);
       // return new ResponseEntity(okmanyDTO1, HttpStatus.OK);
        return new ResponseEntity(response, HttpStatus.OK);

    }
}
