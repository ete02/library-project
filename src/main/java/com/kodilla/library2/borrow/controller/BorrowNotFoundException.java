package com.kodilla.library2.borrow.controller;

public class BorrowNotFoundException extends Exception {
    public BorrowNotFoundException(final String message) {
        super(message);
    }
}
