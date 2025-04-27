package com.example.demo.exceptions;

import java.io.Serial;

public class FileLoadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileLoadException(String msg) {
        super(msg);
    }

}
