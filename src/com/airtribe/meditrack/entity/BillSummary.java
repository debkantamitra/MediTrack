package com.airtribe.meditrack.entity;

public final class BillSummary {
    private final String billId;
    private final String appointmentId;
    private final String patientName;
    private final String doctorName;
    private final double consultationFee;
    private final double taxAmount;
    private final double totalAmount;

    public BillSummary(
            String billId,
            String appointmentId,
            String patientName,
            String doctorName,
            double consultationFee,
            double taxAmount,
            double totalAmount
    ) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.consultationFee = consultationFee;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDisplayText() {
        return "Bill " + billId
                + " appointment: " + appointmentId
                + " patient: " + patientName
                + " doctor: " + doctorName
                + " total: " + totalAmount;
    }
}
