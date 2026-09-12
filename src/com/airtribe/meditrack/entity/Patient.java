package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

public class Patient extends MedicalEntity implements Cloneable {
    private String name;
    private int age;
    private AdmissionStatus admissionStatus;

    public Patient(String id, String name, int age, AdmissionStatus admissionStatus) {
        super(id);
        setName(name);
        setAge(age);
        setAdmissionStatus(admissionStatus);
    }

    public Patient(String id, String name, int age) {
        this(id, name, age, AdmissionStatus.WAITLIST);
    }

    @Override
    public String getDisplayName() {
        return "Patient name: " + name + " age: " + age + " status: " + admissionStatus;
    }

    @Override
    public Patient clone() {
        return new Patient(getId(), name, age, admissionStatus);
    }

    public String getName() {
        return name;
    }

    public AdmissionStatus getAdmissionStatus() {
        return admissionStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (!Validator.isValidAge(age)) {
            throw new InvalidDataException("Patient age must be between 0 and 120.");
        }
        this.age = age;
    }

    public void setName(String name) {
        if (!Validator.isValidName(name)) {
            throw new InvalidDataException("Patient name cannot be blank.");
        }
        this.name = name;
    }

    public void setAdmissionStatus(AdmissionStatus admissionStatus) {
        if (!Validator.isPresent(admissionStatus)) {
            throw new InvalidDataException("Admission status is required.");
        }
        this.admissionStatus = admissionStatus;
    }
}
