package hu.sabikov.idomsoft.assignment.microservice1.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class CustomErrorDetails {

    private final LocalDateTime localDateTime;
    private final String message;
    private final String errorDetail;

}
