package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;

public class EmergencyBillingStrategy implements BillingStrategy {
    private static final double EMERGENCY_SURCHARGE_RATE = 0.25;

    @Override
    public BillSummary generateSummary(String billId, Appointment appointment, double consultationFee) {
        double amountBeforeTax = consultationFee + (consultationFee * EMERGENCY_SURCHARGE_RATE);
        double taxAmount = amountBeforeTax * Constants.TAX_RATE;
        double totalAmount = amountBeforeTax + taxAmount;

        return new BillSummary(
                billId,
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                "EMERGENCY",
                amountBeforeTax,
                taxAmount,
                totalAmount
        );
    }
}
