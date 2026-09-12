package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.util.Validator;

public class Bill extends MedicalEntity implements Payable {
    private Appointment appointment;
    private double consultationFee;

    public Bill(String id, Appointment appointment, double consultationFee) {
        super(id);
        setAppointment(appointment);
        setConsultationFee(consultationFee);
    }

    @Override
    public String getDisplayName() {
        return "Bill id: " + getId()
                + " patient: " + appointment.getPatient().getName()
                + " doctor: " + appointment.getDoctor().getName();
    }

    @Override
    public BillSummary generateBill() {
        double taxAmount = consultationFee * Constants.TAX_RATE;
        double totalAmount = consultationFee + taxAmount;

        return new BillSummary(
                getId(),
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                consultationFee,
                taxAmount,
                totalAmount
        );
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setAppointment(Appointment appointment) {
        if (!Validator.isPresent(appointment)) {
            throw new InvalidDataException("Bill appointment is required.");
        }
        this.appointment = appointment;
    }

    public void setConsultationFee(double consultationFee) {
        if (!Validator.isNonNegative(consultationFee)) {
            throw new InvalidDataException("Consultation fee cannot be negative.");
        }
        this.consultationFee = consultationFee;
    }
}
