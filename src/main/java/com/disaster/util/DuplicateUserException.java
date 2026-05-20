package com.disaster.util;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException(Long id) {
        super("User not found with ID: " + id);
    }
}
