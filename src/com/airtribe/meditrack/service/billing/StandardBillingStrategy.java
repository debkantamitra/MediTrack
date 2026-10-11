package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;

public class StandardBillingStrategy implements BillingStrategy {
    @Override
    public BillSummary generateSummary(String billId, Appointment appointment, double consultationFee) {
        double taxAmount = consultationFee * Constants.TAX_RATE;
        double totalAmount = consultationFee + taxAmount;

        return new BillSummary(
                billId,
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                "STANDARD",
                consultationFee,
                taxAmount,
                totalAmount
        );
    }
}
