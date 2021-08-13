package com.aarnasolutions.learning.gameoflife.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GameOfLifeExceptionHandler {

    @ExceptionHandler(value = GameIdDoesNotExistException.class)
    public ResponseEntity<Object> exception(GameIdDoesNotExistException exception) {
        GameOfLifeExceptionResponse gameOfLifeExceptionResponse = new GameOfLifeExceptionResponse();
        gameOfLifeExceptionResponse.setErrorMessage("Game Id " + exception.getGameId() + " not found");
        gameOfLifeExceptionResponse.setErrorTime(ZonedDateTime.now());
        return new ResponseEntity<>(gameOfLifeExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = GameAlreadyFullOfPlayersException.class)
    public ResponseEntity<Object> exception(GameAlreadyFullOfPlayersException exception) {
        GameOfLifeExceptionResponse gameOfLifeExceptionResponse = new GameOfLifeExceptionResponse();
        gameOfLifeExceptionResponse.setErrorMessage("Game already has " + exception.getMaxPermissiblePlayers() + " players");
        gameOfLifeExceptionResponse.setErrorTime(ZonedDateTime.now());
        return new ResponseEntity<>(gameOfLifeExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CellIdDoesNotExistException.class)
    public ResponseEntity<Object> exception(CellIdDoesNotExistException exception) {
        GameOfLifeExceptionResponse gameOfLifeExceptionResponse = new GameOfLifeExceptionResponse();
        gameOfLifeExceptionResponse.setErrorMessage("Cell Id " + exception.getCellId() + " not found");
        gameOfLifeExceptionResponse.setErrorTime(ZonedDateTime.now());
        return new ResponseEntity<>(gameOfLifeExceptionResponse, HttpStatus.NOT_FOUND);
    }
}
