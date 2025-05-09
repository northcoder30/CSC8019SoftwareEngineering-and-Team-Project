package com.ebooksystem.ebookmanagementsystem.exceptions;

public class BorrowRecordExistsException extends RuntimeException {
    public BorrowRecordExistsException(String message) {
        super(message);
    }
}
