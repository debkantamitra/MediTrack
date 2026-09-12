package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class DoctorService implements Searchable<Doctor, Specialization> {
    private final DataStore<Doctor> doctorStore;

    public DoctorService() {
        this.doctorStore = new DataStore<>();
    }

    public void addDoctor(Doctor doctor) {
        doctorStore.save(doctor);
    }

    public Doctor findDoctorById(String id) {
        return doctorStore.findById(id);
    }

    public List<Doctor> findAllDoctors() {
        return doctorStore.findAll();
    }

    @Override
    public List<Doctor> search(Specialization specialization) {
        if (!Validator.isPresent(specialization)) {
            throw new InvalidDataException("Doctor specialization search value is required.");
        }

        List<Doctor> matches = new ArrayList<>();
        for (Doctor doctor : doctorStore.findAll()) {
            if (doctor.getSpecialization() == specialization) {
                matches.add(doctor);
            }
        }
        return matches;
    }

    public List<Doctor> searchDoctorsByName(String name) {
        if (!Validator.isValidName(name)) {
            throw new InvalidDataException("Doctor search name cannot be blank.");
        }

        List<Doctor> matches = new ArrayList<>();
        for (Doctor doctor : doctorStore.findAll()) {
            if (containsIgnoreCase(doctor.getName(), name)) {
                matches.add(doctor);
            }
        }
        return matches;
    }

    public void updateDoctor(String id, String name, Specialization specialization, double experience) {
        Doctor doctor = findRequiredDoctor(id);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setExperience(experience);
    }

    public boolean deleteDoctor(String id) {
        return doctorStore.deleteById(id);
    }

    public int countDoctors() {
        return doctorStore.size();
    }

    private Doctor findRequiredDoctor(String id) {
        if (!Validator.isValidName(id)) {
            throw new InvalidDataException("Doctor id cannot be blank.");
        }

        Doctor doctor = doctorStore.findById(id);
        if (!Validator.isPresent(doctor)) {
            throw new InvalidDataException("Doctor not found for id: " + id);
        }
        return doctor;
    }
}
