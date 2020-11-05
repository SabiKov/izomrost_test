package hu.sabikov.idomsoft.assignment.microservice1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
class NameValidatorServiceImplTest {


    private NameValidatorService nameValidatorService;

    @BeforeEach
    void initTest() {
        nameValidatorService = new NameValidatorServiceImpl();
    }

    @Test
    void isContainsBothNamesValidTest() {
        assertTrue(nameValidatorService.isContainsBothNames("Kovacs Szabolcs"));
        assertTrue(nameValidatorService.isContainsBothNames("Dr. Szabó Elek"));
        assertTrue(nameValidatorService.isContainsBothNames("Dr.Kiss Feri"));
        assertTrue(nameValidatorService.isContainsBothNames("Ispán Zoli dr."));
        assertTrue(nameValidatorService.isContainsBothNames("Dr. Elek"));

        assertFalse(nameValidatorService.isContainsBothNames("KovacsSzabolcs"));

        assertTrue(nameValidatorService.isContainsBothNames("Mr. Kiss Feri"));
    }

    @Test
    void isContainUnwantedChar() {
        String availableChars =
                "ÄAÁBCDEFGHIÍJKLMNOÓÖŐPQRSTYZWVaábcdefghiíjklmnoóöőpqrstyzwv./'- ";
        char[] chars = availableChars.toCharArray();
        for(char c : chars) {
            assertFalse(nameValidatorService.isContainUnwantedChar(c + ""));
        }
        assertTrue(nameValidatorService.isContainUnwantedChar("("));
        assertTrue(nameValidatorService.isContainUnwantedChar(")"));
        assertTrue(nameValidatorService.isContainUnwantedChar("*"));
        assertTrue(nameValidatorService.isContainUnwantedChar("+"));
        assertTrue(nameValidatorService.isContainUnwantedChar(","));
        assertTrue(nameValidatorService.isContainUnwantedChar(";"));
        assertTrue(nameValidatorService.isContainUnwantedChar(">"));
        assertTrue(nameValidatorService.isContainUnwantedChar("{"));
        assertTrue(nameValidatorService.isContainUnwantedChar("}"));
        assertTrue(nameValidatorService.isContainUnwantedChar("#"));
        assertTrue(nameValidatorService.isContainUnwantedChar("&"));
        assertTrue(nameValidatorService.isContainUnwantedChar("@"));
        assertTrue(nameValidatorService.isContainUnwantedChar("<"));
        assertTrue(nameValidatorService.isContainUnwantedChar("đ"));
        assertTrue(nameValidatorService.isContainUnwantedChar("Đ"));
        assertTrue(nameValidatorService.isContainUnwantedChar("$"));
        assertTrue(nameValidatorService.isContainUnwantedChar("Ł"));
        assertTrue(nameValidatorService.isContainUnwantedChar("ß"));
        assertTrue(nameValidatorService.isContainUnwantedChar("ł"));
        assertTrue(nameValidatorService.isContainUnwantedChar("¤"));
        assertTrue(nameValidatorService.isContainUnwantedChar("×"));
        assertTrue(nameValidatorService.isContainUnwantedChar("÷"));
    }

    @Test
    void isTooLengthy() {
        String length79 = "        10        20        30        40        50        60        70       79";
        String length80 = "        10        20        30        40        50        60        70        80";
        String length81 = "        10        20        30        40        50        60        70         81";
        String lengthEmpty = "";

        assertFalse(nameValidatorService.isTooLengthy(length79));
        assertFalse(nameValidatorService.isTooLengthy(length80));
        assertTrue(nameValidatorService.isTooLengthy(length81));
//        assertFalse(nameValidatorService.isTooLengthy(null));
        assertFalse(nameValidatorService.isTooLengthy(lengthEmpty));
    }
}
