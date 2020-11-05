package hu.sabikov.idomsoft.assignment.microservice2.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class CustomErrorDetails {

    private final LocalDateTime localDateTime;
    private final String message;
    private final String errorDetail;

}
