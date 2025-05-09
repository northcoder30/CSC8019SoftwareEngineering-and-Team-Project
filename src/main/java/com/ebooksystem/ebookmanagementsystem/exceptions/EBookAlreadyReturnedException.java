package com.ebooksystem.ebookmanagementsystem.exceptions;

public class EBookAlreadyReturnedException extends RuntimeException {
    public EBookAlreadyReturnedException(String message) {
        super(message);
    }
}
