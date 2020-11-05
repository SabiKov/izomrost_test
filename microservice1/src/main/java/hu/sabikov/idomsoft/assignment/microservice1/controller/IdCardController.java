package hu.sabikov.idomsoft.assignment.microservice1.controller;

import hu.sabikov.idomsoft.assignment.microservice1.exception.InvalidRequestedParamsException;
import hu.sabikov.idomsoft.assignment.microservice1.model.KodSzotar21Allampolg;
import hu.sabikov.idomsoft.assignment.microservice1.model.OkmanyDTO;
import hu.sabikov.idomsoft.assignment.microservice1.model.Row;
import hu.sabikov.idomsoft.assignment.microservice1.model.SzemelyDTO;
import hu.sabikov.idomsoft.assignment.microservice1.service.DataErrorService;
import hu.sabikov.idomsoft.assignment.microservice1.service.KodSzotar21AllampolgServiceImpl;
import hu.sabikov.idomsoft.assignment.microservice1.service.OkmanyDtoService;
import hu.sabikov.idomsoft.assignment.microservice1.service.RequestProcessStep;
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
@RequestMapping("/mcrsrv1")
public class IdCardController {

    @Autowired
    RequestProcessStep requestProcessStep;

    @Autowired
    KodSzotar21AllampolgServiceImpl kodSzotar21AllampolgService;

    @Autowired
    OkmanyDtoService okmanyDtoService;

    @Autowired
    DataErrorService dataErrorService;

    @PostMapping("szemelydto")
    public ResponseEntity processRequestedData(
            @RequestBody SzemelyDTO szemelyDTO) {

        ResponseEntity<Object> response = null;

        try {
            response = requestProcessStep.processWorkFlow(szemelyDTO);
        } catch (InvalidRequestedParamsException e) {
            response = new ResponseEntity<>(dataErrorService.getInfoAboutIncorrectData(), HttpStatus.FORBIDDEN);
            e.printStackTrace();

        }

//        SzemelyDTO szemelyDTOFake = new SzemelyDTO();
//        szemelyDTOFake.setVisNev("Kovács Szabolcs visNev");
//        szemelyDTOFake.setSzulNev("Kovács Szabolcs szulNev");
//        szemelyDTOFake.setANev("Kovács Szabolcs aNév");
//        String born = "1978-03-11";
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date szulDate = null;
//        try {
//            szulDate = formatter.parse(born);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        szemelyDTOFake.setSzulDat(szulDate);
//        szemelyDTOFake.setNeme("F");
//        szemelyDTOFake.setAllampKod("KIR");
//     //   szemelyDTOFake.setAllampDekod("KIRIBATI ÁLLAMPOLGÁRA");
//        ArrayList<OkmanyDTO> okmanyList = new ArrayList<>();
//        OkmanyDTO passport = new OkmanyDTO();
//        passport.setOkmTipus("1");
//        passport.setOkmanySzam("1");
//        byte[] pic = {12, 4, 3};
//        passport.setOkmanyKep(pic);
//        String expired = "2020-03-11";
//        SimpleDateFormat formatterExpired = new SimpleDateFormat("yyyy-MM-dd");
//        Date docExpired = null;
//        try {
//            docExpired = formatter.parse(expired);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        passport.setLejarDat(docExpired);
//        passport.setErvenyes(false);
//        okmanyList.add(passport);
//        szemelyDTOFake.setOkmLista(okmanyList);

 //       ResponseEntity result = okmanyDtoService.getOkmanyDtoResponse(passport);

        List<String> hiba = new ArrayList<>();
        hiba.add("hiba név");
        hiba.add("hiba valami");
        hiba.add("hiba dátum");
        hiba.add("hiba okmány");

        return new ResponseEntity(hiba, HttpStatus.OK);
    }

}
