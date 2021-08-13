package com.aarnasolutions.learning.gameoflife.exception;

import lombok.Getter;

@Getter
public class CellIdDoesNotExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final long cellId;

    public CellIdDoesNotExistException(long cellId) {
        this.cellId = cellId;
    }
}
