package hu.sabikov.idomsoft.assignment.microservice2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class OkmanyErvenyesegEllenorzesServiceImpl implements OkmanyErvenyesegEllenorzesService {

    private final String dateFormat = "yyyy-MM-dd";

    @Override
    public boolean isValidFormat(String date) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(Date lejaratDatum) {
        LocalDate today = LocalDate.now();
        LocalDate lejaratiDatum = convertDateToLocalDate(lejaratDatum);
        return lejaratDatum != null &&
                today.compareTo(lejaratiDatum) != -1;
    }
    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
