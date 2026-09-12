package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.service.billing.BillingStrategy;
import com.airtribe.meditrack.service.billing.StandardBillingStrategy;
import com.airtribe.meditrack.util.Validator;

public class Bill extends MedicalEntity implements Payable {
    private Appointment appointment;
    private double consultationFee;
    private BillingStrategy billingStrategy;

    public Bill(String id, Appointment appointment, double consultationFee) {
        this(id, appointment, consultationFee, new StandardBillingStrategy());
    }

    public Bill(String id, Appointment appointment, double consultationFee, BillingStrategy billingStrategy) {
        super(id);
        setAppointment(appointment);
        setConsultationFee(consultationFee);
        setBillingStrategy(billingStrategy);
    }

    @Override
    public String getDisplayName() {
        return "Bill id: " + getId()
                + " patient: " + appointment.getPatient().getName()
                + " doctor: " + appointment.getDoctor().getName();
    }

    @Override
    public BillSummary generateBill() {
        return billingStrategy.generateSummary(getId(), appointment, consultationFee);
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public BillingStrategy getBillingStrategy() {
        return billingStrategy;
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

    public void setBillingStrategy(BillingStrategy billingStrategy) {
        if (!Validator.isPresent(billingStrategy)) {
            throw new InvalidDataException("Billing strategy is required.");
        }
        this.billingStrategy = billingStrategy;
    }
}
