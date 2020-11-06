package hu.sabikov.idomsoft.assignment.microservice2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
class OkmanyErvenyesegEllenorzesServiceImplTest {

    private OkmanyErvenyesegEllenorzesService okmanyErvenyesegEllenorzesService;

    @BeforeEach
    void initTest() {
        this.okmanyErvenyesegEllenorzesService = new OkmanyErvenyesegEllenorzesServiceImpl();
    }

    @Test
    void isValidFormatTest() {

        assertTrue(okmanyErvenyesegEllenorzesService.isValidFormat("2000-01-01"));
        assertFalse(okmanyErvenyesegEllenorzesService.isValidFormat("2000-14-01"));
        assertFalse(okmanyErvenyesegEllenorzesService.isValidFormat(""));
        assertFalse(okmanyErvenyesegEllenorzesService.isValidFormat(null));

    }

    @Test
    void isExpiredTest() {
        String dateS1 = "2020-12-01";
        String dateS2 = "2020-10-01";
        String dateS3 = "2020-11-06";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        try {
            date1 = sdf.parse(dateS1);
            date2 = sdf.parse(dateS2);
            date3 = sdf.parse(dateS3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertFalse(okmanyErvenyesegEllenorzesService.isExpired(date1));
        assertTrue(okmanyErvenyesegEllenorzesService.isExpired(date2));
        assertFalse(okmanyErvenyesegEllenorzesService.isExpired(date3));
    }
}
