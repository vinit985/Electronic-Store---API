package com.electronic.store.exception;

public class IncorrectFileUploadException extends RuntimeException {


    public IncorrectFileUploadException()
    {
        super("Wrong file uploaded with some different extension");
    }

    public IncorrectFileUploadException(String message)
    {
        super(message);
    }
}
