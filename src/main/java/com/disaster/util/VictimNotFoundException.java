package com.disaster.util;

public class VictimNotFoundException extends RuntimeException {
    public VictimNotFoundException(String message) {
        super(message);
    }

    public VictimNotFoundException(Long id) {
        super("Victim not found with ID: " + id);
    }
}
