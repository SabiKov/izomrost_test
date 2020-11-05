package hu.sabikov.idomsoft.assignment.microservice2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
class OkmanySzamValidatorServiceImplTest {

    private OkmanySzamValidatorService okmanySzamValidatorService;

    @BeforeEach
    void initTest() {
        this.okmanySzamValidatorService =
                new OkmanySzamValidatorServiceImpl();
    }

    @Test
    void isValid() {
    }

    @Test
    void isValidSzigTest() {
        assertTrue(this.okmanySzamValidatorService.isValidSzig("123456AS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("123456as"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("12345AAS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("1235A6A3"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("D1234 AS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("12345 AS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("12345 AS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("12345 AS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig("12345AS"));
        assertFalse(this.okmanySzamValidatorService.isValidSzig(""));
        assertFalse(this.okmanySzamValidatorService.isValidSzig(null));

    }

    @Test
    void isValidUtlTest() {
        assertTrue(this.okmanySzamValidatorService.isValidUtl("DS1234567"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("ad1234567"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("DSS123457"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("12 5 A6A3"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("D3!134 AS"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("12345  AS"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("ER 123456"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("a 1a234AS"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl("aS5678965"));
        assertFalse(this.okmanySzamValidatorService.isValidUtl(""));
        assertFalse(this.okmanySzamValidatorService.isValidUtl(null));
    }

    @Test
    void isValidVen() {
        assertTrue(this.okmanySzamValidatorService.isValidVen("1234567890"));
        assertFalse(this.okmanySzamValidatorService.isValidVen("ad123 34567"));
        assertFalse(this.okmanySzamValidatorService.isValidVen("D33SS123457"));
        assertFalse(this.okmanySzamValidatorService.isValidVen("12   5 A6A3"));
        assertFalse(this.okmanySzamValidatorService.isValidVen(" D3!134 A S"));
        assertFalse(this.okmanySzamValidatorService.isValidVen(" 12345  345"));
        assertFalse(this.okmanySzamValidatorService.isValidVen("ER 1  23456"));
        assertFalse(this.okmanySzamValidatorService.isValidVen("a 1  a234AS"));
        assertFalse(this.okmanySzamValidatorService.isValidVen("aSfr5678965"));
        assertFalse(this.okmanySzamValidatorService.isValidVen(""));
        assertFalse(this.okmanySzamValidatorService.isValidVen(null));
    }
}
