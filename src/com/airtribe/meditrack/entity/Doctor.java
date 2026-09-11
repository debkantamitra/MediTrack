package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

public class Doctor extends MedicalEntity {
    private String name;
    private String specialization;
    private double experience;

    public Doctor(String id, String name, String specialization) {
        this(id, name, specialization, 0);
    }

    public Doctor(String id, String name, String specialization, double experience) {
        super(id);
        setName(name);
        setSpecialization(specialization);
        setExperience(experience);
    }

    @Override
    public String getDisplayName() {
        return "Doctor name: " + name + " specialization: " + specialization + " experience: " + experience;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public double getExperience() {
        return experience;
    }

    public void setName(String name) {
        if (!Validator.isValidName(name)) {
            throw new InvalidDataException("Doctor name cannot be blank.");
        }
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        if (!Validator.isValidName(specialization)) {
            throw new InvalidDataException("Doctor specialization cannot be blank.");
        }
        this.specialization = specialization;
    }

    public void setExperience(double experience) {
        if (!Validator.isNonNegative(experience)) {
            throw new InvalidDataException("Doctor experience cannot be negative.");
        }
        this.experience = experience;
    }
}
