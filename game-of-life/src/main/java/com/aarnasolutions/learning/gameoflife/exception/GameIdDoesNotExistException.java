package com.aarnasolutions.learning.gameoflife.exception;

import lombok.Getter;

@Getter
public class GameIdDoesNotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String gameId;

    public GameIdDoesNotExistException(String gameId) {
        this.gameId = gameId;
    }
}
