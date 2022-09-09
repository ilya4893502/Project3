package com.spring.project.util.exceptions;

public class PersonError {

    private String message;

    public PersonError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
