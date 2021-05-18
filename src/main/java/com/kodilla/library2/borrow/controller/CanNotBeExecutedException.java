package com.kodilla.library2.borrow.controller;

public class CanNotBeExecutedException extends Exception {
    public CanNotBeExecutedException(final String message) {
        super(message);
    }
}
