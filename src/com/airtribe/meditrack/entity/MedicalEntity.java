package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

public abstract class MedicalEntity {
    private final String id;
    private boolean active;

    protected MedicalEntity(String id) {
        if (!Validator.isValidName(id)) {
            throw new InvalidDataException("Medical entity id cannot be blank.");
        }
        this.id = id;
        this.active = true;
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public abstract String getDisplayName();
}
