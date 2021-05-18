package com.kodilla.library2.book.controller;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(final String message) {
        super(message);
    }
}