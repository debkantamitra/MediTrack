package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

public class Doctor extends MedicalEntity {
    private String name;
    private Specialization specialization;
    private double experience;

    public Doctor(String id, String name, Specialization specialization) {
        this(id, name, specialization, 0);
    }

    public Doctor(String id, String name, Specialization specialization, double experience) {
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

    public Specialization getSpecialization() {
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

    public void setSpecialization(Specialization specialization) {
        if (!Validator.isPresent(specialization)) {
            throw new InvalidDataException("Doctor specialization is required.");
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
