package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.EntityNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService implements Searchable<Appointment, AppointmentStatus> {
    private final DataStore<Appointment> appointmentStore;

    public AppointmentService() {
        this.appointmentStore = new DataStore<>();
    }

    public Appointment createAppointment(String id, Doctor doctor, Patient patient, Date appointmentDate) {
        Appointment appointment = new Appointment(id, doctor, patient, appointmentDate, AppointmentStatus.CONFIRMED);
        appointmentStore.save(appointment);
        return appointment;
    }

    public Appointment findAppointmentById(String id) {
        return appointmentStore.findById(id);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentStore.findAll();
    }

    @Override
    public List<Appointment> search(AppointmentStatus status) {
        if (!Validator.isPresent(status)) {
            throw new InvalidDataException("Appointment status search value is required.");
        }

        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointmentStore.findAll()) {
            if (appointment.getStatus() == status) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    public void cancelAppointment(String id) {
        Appointment appointment = findRequiredAppointment(id);
        appointment.cancel();
    }

    public int countAppointments() {
        return appointmentStore.size();
    }

    private Appointment findRequiredAppointment(String id) {
        if (!Validator.isValidName(id)) {
            throw new InvalidDataException("Appointment id cannot be blank.");
        }

        Appointment appointment = appointmentStore.findById(id);
        if (!Validator.isPresent(appointment)) {
            throw new EntityNotFoundException("Appointment", id);
        }
        return appointment;
    }
}
