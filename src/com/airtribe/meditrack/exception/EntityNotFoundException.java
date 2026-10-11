package com.airtribe.meditrack.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String id) {
        super(entityName + " not found for id: " + id);
    }
}
