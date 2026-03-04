package com.electronic.store.dtos;


import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponseMessage {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;
}
