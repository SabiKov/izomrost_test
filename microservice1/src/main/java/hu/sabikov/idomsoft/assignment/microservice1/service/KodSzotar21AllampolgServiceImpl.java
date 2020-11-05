package hu.sabikov.idomsoft.assignment.microservice1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.sabikov.idomsoft.assignment.microservice1.model.KodSzotar21Allampolg;
import hu.sabikov.idomsoft.assignment.microservice1.model.Row;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@Service
@Slf4j
public class KodSzotar21AllampolgServiceImpl implements KodSzotar21AllampolgService{

    @Autowired
    ResourceLoader resourceLoader;

    private List<KodSzotar21Allampolg> szotar;

//    public void addKodSzotar21Allampolg(KodSzotar21Allampolg kodSzotar21Allampolg) {
//        System.out.println(kodSzotar21Allampolg);
//        szotar.add(kodSzotar21Allampolg);
//    }

    private KodSzotar21Allampolg getKodSzotar21Allampolg() {
        Resource resource = resourceLoader.getResource("classpath:json/kodszotar21_allampolg.json");
        try {
            File file = resource.getFile();
            ObjectMapper mapper = new ObjectMapper();
            KodSzotar21Allampolg kodSzotar21Allampolg = mapper.readValue(file, KodSzotar21Allampolg.class);
            log.info("39 kodSzotar21Allampolg: {}", kodSzotar21Allampolg);
            return kodSzotar21Allampolg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findAllampDekod(String allampKod) {
        KodSzotar21Allampolg szotar = getKodSzotar21Allampolg();
        System.out.println(szotar);
        List<Row> rows = szotar.getRows();

        String allamDeKod = rows
                .stream()
                .filter(row -> row.getAllampolgarsag().equals(allampKod))
                .findAny()
                .orElse(null)
                .getAllampolgarsag();
        log.info("allamDekod {} ", allamDeKod);
        return allamDeKod;
    }
}
