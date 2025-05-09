package com.ebooksystem.ebookmanagementsystem.exceptions;

public class EbookAlreadyBorrowedException extends RuntimeException {
    public EbookAlreadyBorrowedException(String message) {
        super(message);
    }
}
