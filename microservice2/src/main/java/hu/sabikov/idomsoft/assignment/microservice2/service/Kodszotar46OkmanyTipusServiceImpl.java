package hu.sabikov.idomsoft.assignment.microservice2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.sabikov.idomsoft.assignment.microservice2.model.KodSzotar46OkmanyTipus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class Kodszotar46OkmanyTipusServiceImpl implements Kodszotar46OkmanyTipusService {

    @Autowired
    ResourceLoader resourceLoader;

    private List<Kodszotar46OkmanyTipusService> szotar;

    public KodSzotar46OkmanyTipus getKodSzotar46OkmanyTipus() {
        Resource resource = resourceLoader
                .getResource("classpath:json/kodszotar46_okmanytipus");
        try {
            File file = resource.getFile();
            ObjectMapper mapper = new ObjectMapper();
            KodSzotar46OkmanyTipus KodSzotar46OkmanyTipus = mapper
                    .readValue(file,
                            KodSzotar46OkmanyTipus.class);
            System.out.println(KodSzotar46OkmanyTipus);
            return KodSzotar46OkmanyTipus;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
