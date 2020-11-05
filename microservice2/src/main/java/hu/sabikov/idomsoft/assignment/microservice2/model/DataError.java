package hu.sabikov.idomsoft.assignment.microservice2.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Component
public class DataError {

    private static List<String> infoAboutIncorrectData;

    static {
        infoAboutIncorrectData =  new ArrayList<>();
    }

    private DataError() {}

    public static void addInfoAboutIncorrectData(String msg) {
        infoAboutIncorrectData.add(msg);
    }

    public static int sizeOfInfoAboutIncorrectData() {
        return infoAboutIncorrectData.size();
    }

    public static List<String> getInfoAboutIncorrectData() {
        return infoAboutIncorrectData;
    }
}
