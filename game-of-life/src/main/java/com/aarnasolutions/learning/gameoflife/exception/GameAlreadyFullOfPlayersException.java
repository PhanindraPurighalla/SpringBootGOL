package com.aarnasolutions.learning.gameoflife.exception;

import lombok.Getter;

@Getter
public class GameAlreadyFullOfPlayersException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int maxPermissiblePlayers;

    public GameAlreadyFullOfPlayersException(int maxPermissiblePlayers) {
        this.maxPermissiblePlayers = maxPermissiblePlayers;
    }
}
