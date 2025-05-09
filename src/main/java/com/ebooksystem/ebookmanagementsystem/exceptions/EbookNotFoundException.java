package com.ebooksystem.ebookmanagementsystem.exceptions;

public class EbookNotFoundException extends RuntimeException {
    public EbookNotFoundException(String message) {
        super(message);
    }
}
