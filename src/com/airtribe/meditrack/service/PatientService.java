package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.AdmissionStatus;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.EntityNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class PatientService implements Searchable<Patient, String> {
    private final DataStore<Patient> patientStore;

    public PatientService() {
        this.patientStore = new DataStore<>();
    }

    public void addPatient(Patient patient) {
        patientStore.save(patient);
    }

    public Patient findPatientById(String id) {
        return patientStore.findById(id);
    }

    public List<Patient> findAllPatients() {
        return patientStore.findAll();
    }

    @Override
    public List<Patient> search(String name) {
        return searchPatientsByName(name);
    }

    public Patient searchPatient(String id) {
        return findPatientById(id);
    }

    public List<Patient> searchPatient(int age) {
        List<Patient> matches = new ArrayList<>();
        for (Patient patient : patientStore.findAll()) {
            if (patient.getAge() == age) {
                matches.add(patient);
            }
        }
        return matches;
    }

    public List<Patient> searchPatientsByName(String name) {
        if (!Validator.isValidName(name)) {
            throw new InvalidDataException("Patient search name cannot be blank.");
        }

        List<Patient> matches = new ArrayList<>();
        for (Patient patient : patientStore.findAll()) {
            if (containsIgnoreCase(patient.getName(), name)) {
                matches.add(patient);
            }
        }
        return matches;
    }

    public void updatePatient(String id, String name, int age, AdmissionStatus admissionStatus) {
        Patient patient = findRequiredPatient(id);
        patient.setName(name);
        patient.setAge(age);
        patient.setAdmissionStatus(admissionStatus);
    }

    public boolean deletePatient(String id) {
        return patientStore.deleteById(id);
    }

    public int countPatients() {
        return patientStore.size();
    }

    private Patient findRequiredPatient(String id) {
        if (!Validator.isValidName(id)) {
            throw new InvalidDataException("Patient id cannot be blank.");
        }

        Patient patient = patientStore.findById(id);
        if (!Validator.isPresent(patient)) {
            throw new EntityNotFoundException("Patient", id);
        }
        return patient;
    }
}
