package com.biblios.huceng.exception;

public class PastDateException extends RuntimeException {
    public PastDateException() {
        super("Date is in the past.");
    }
}
