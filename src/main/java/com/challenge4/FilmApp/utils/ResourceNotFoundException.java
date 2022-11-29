package com.challenge4.FilmApp.utils;


public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}