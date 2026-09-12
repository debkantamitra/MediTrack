package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

import java.util.Date;

public class Appointment extends MedicalEntity {
    private Doctor doctor;
    private Patient patient;
    private Date appointmentDate;
    private AppointmentStatus status;

    public Appointment(String id, Doctor doctor, Patient patient, Date appointmentDate) {
        this(id, doctor, patient, appointmentDate, AppointmentStatus.PENDING);
    }

    public Appointment(String id, Doctor doctor, Patient patient, Date appointmentDate, AppointmentStatus status) {
        super(id);
        setDoctor(doctor);
        setPatient(patient);
        setAppointmentDate(appointmentDate);
        setStatus(status);
    }

    @Override
    public String getDisplayName() {
        return "Doctor name: " + doctor.getName()
                + " Patient name: " + patient.getName()
                + " appointment at: " + appointmentDate
                + " status: " + status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime());
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setDoctor(Doctor doctor) {
        if (!Validator.isPresent(doctor)) {
            throw new InvalidDataException("Appointment doctor is required.");
        }
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        if (!Validator.isPresent(patient)) {
            throw new InvalidDataException("Appointment patient is required.");
        }
        this.patient = patient;
    }

    public void setAppointmentDate(Date appointmentDate) {
        if (!Validator.isPresent(appointmentDate)) {
            throw new InvalidDataException("Appointment date is required.");
        }
        this.appointmentDate = new Date(appointmentDate.getTime());
    }

    public void setStatus(AppointmentStatus status) {
        if (!Validator.isPresent(status)) {
            throw new InvalidDataException("Appointment status is required.");
        }
        this.status = status;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }
}
