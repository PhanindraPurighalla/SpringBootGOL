package com.aarnasolutions.learning.gameoflife.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class GameOfLifeExceptionResponse {
    private String errorMessage;
    private ZonedDateTime errorTime;
}
