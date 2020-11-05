package hu.sabikov.idomsoft.assignment.microservice1.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SzuletesiEvValidatorServiceImpl implements SzuletesiEvValidatorService {


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
    public boolean isAdult(int age) {
        return age >= 18;
    }

    @Override
    public boolean isAlive(int age) {
        return age <= 120;
    }

    @Override
    public boolean isAgeInRange(int age) {
        return isAdult(age) && isAlive(age);
    }

    @Override
    public int calculateAge(Date date) {
        LocalDate today = LocalDate.now();
        Date birthdayDate = date;
        LocalDate birthday = convertDateToLocalDate(birthdayDate);
        Period period = Period.between(birthday, today);
        return period.getYears();
    }

    @Override
    public boolean isErvenyesSzuletesiEv(Date date) {
        int age = calculateAge(date);
        return isAgeInRange(age);
    }

    @Override
    public boolean isValidAge(Date date) {
        return isErvenyesSzuletesiEv(date);
    }

    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date stringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = null;
        try {
            convertedDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
}
