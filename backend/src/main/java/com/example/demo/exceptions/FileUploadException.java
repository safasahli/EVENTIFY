package com.example.demo.exceptions;

import java.io.Serial;

public class FileUploadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileUploadException(String msg) {
        super(msg);
    }
}
