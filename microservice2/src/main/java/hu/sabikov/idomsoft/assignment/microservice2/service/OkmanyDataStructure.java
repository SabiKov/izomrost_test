package hu.sabikov.idomsoft.assignment.microservice2.service;

import hu.sabikov.idomsoft.assignment.microservice2.model.OkmanyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class OkmanyDataStructure {

    @Autowired
    OkmanyErvenyesegEllenorzesService okmanyErvenyesegEllenorzesService;

    private ArrayList<OkmanyDTO> okmanyDTOListType1;
    private ArrayList<OkmanyDTO> okmanyDTOListType2;
    private ArrayList<OkmanyDTO> okmanyDTOListType3;
    private ArrayList<OkmanyDTO> okmanyDTOListType4;
    private ArrayList<OkmanyDTO> okmanyDTOListType5;

    {
        okmanyDTOListType1 = new ArrayList<>();
        okmanyDTOListType2 = new ArrayList<>();
        okmanyDTOListType3 = new ArrayList<>();
        okmanyDTOListType4 = new ArrayList<>();
        okmanyDTOListType5 = new ArrayList<>();
    }

    public void addOkmanyDtoToList(OkmanyDTO okmanyDTO) {
        log.info("okmanyDTO {} ", okmanyDTO);
        switch(okmanyDTO.getOkmTipus()) {
            case "1":
                okmanyDTOListType1.add(okmanyDTO);
                break;
            case "2":
                okmanyDTOListType2.add(okmanyDTO);
                break;
            case "3":
                okmanyDTOListType3.add(okmanyDTO);
                break;
            case "4":
                okmanyDTOListType4.add(okmanyDTO);
                break;
            case "5":
                okmanyDTOListType5.add(okmanyDTO);
            default:
                log.info("Invalid okmany tipus");
        }
    }

    public boolean isMultipleValidOkmany() {
        return countDocetType1ContainsMultipleValid() > 1 ||
                countDocetType2ContainsMultipleValid() > 1 ||
                countDocetType3ContainsMultipleValid() > 1 ||
                countDocetType4ContainsMultipleValid() > 1 ||
                countDocetType5ContainsMultipleValid() > 1;

    }

    private int countDocetType1ContainsMultipleValid() {
        int countMultipleValidDocet = 0;
        for(OkmanyDTO okmanyDTO : okmanyDTOListType1) {
            if(!okmanyErvenyesegEllenorzesService.isExpired(okmanyDTO.getLejarDat())) {
                log.info("okmány 1 érvényes {} ", countMultipleValidDocet);
                countMultipleValidDocet++;
            }
        }
        return countMultipleValidDocet;
    }

    private int countDocetType2ContainsMultipleValid() {
        int countMultipleValidDocet = 0;
        for(OkmanyDTO okmanyDTO : okmanyDTOListType2) {
            if(!okmanyErvenyesegEllenorzesService.isExpired(okmanyDTO.getLejarDat())) {
                log.info("okmány 2 érvényes {} ", countMultipleValidDocet);
                countMultipleValidDocet++;
            }
        }
        return countMultipleValidDocet;
    }

    private int countDocetType3ContainsMultipleValid() {
        int countMultipleValidDocet = 0;
        for(OkmanyDTO okmanyDTO : okmanyDTOListType3) {
            if(!okmanyErvenyesegEllenorzesService.isExpired(okmanyDTO.getLejarDat())) {
                log.info("okmány 3 érvényes {} ", countMultipleValidDocet);
                countMultipleValidDocet++;
            }
        }
        return countMultipleValidDocet;
    }

    private int countDocetType4ContainsMultipleValid() {
        int countMultipleValidDocet = 0;
        for(OkmanyDTO okmanyDTO : okmanyDTOListType4) {
            if(!okmanyErvenyesegEllenorzesService.isExpired(okmanyDTO.getLejarDat())) {
                log.info("okmány 4 érvényes {} ", countMultipleValidDocet);
                countMultipleValidDocet++;
            }
        }
        return countMultipleValidDocet;
    }

    private int countDocetType5ContainsMultipleValid() {
        int countMultipleValidDocet = 0;
        for(OkmanyDTO okmanyDTO : okmanyDTOListType5) {
            if(!okmanyErvenyesegEllenorzesService.isExpired(okmanyDTO.getLejarDat())) {
                log.info("okmány 5 érvényes {} ", countMultipleValidDocet);
                countMultipleValidDocet++;
            }
        }
        return countMultipleValidDocet;
    }

    public ArrayList<OkmanyDTO> mergeIntoSingleList() {
        return (ArrayList<OkmanyDTO>) Stream.of(
                okmanyDTOListType1,
                okmanyDTOListType2,
                okmanyDTOListType3,
                okmanyDTOListType4,
                okmanyDTOListType5)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
