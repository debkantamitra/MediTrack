package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;

public class FollowUpBillingStrategy implements BillingStrategy {
    private static final double FOLLOW_UP_DISCOUNT_RATE = 0.20;

    @Override
    public BillSummary generateSummary(String billId, Appointment appointment, double consultationFee) {
        double amountBeforeTax = consultationFee - (consultationFee * FOLLOW_UP_DISCOUNT_RATE);
        double taxAmount = amountBeforeTax * Constants.TAX_RATE;
        double totalAmount = amountBeforeTax + taxAmount;

        return new BillSummary(
                billId,
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                "FOLLOW_UP",
                amountBeforeTax,
                taxAmount,
                totalAmount
        );
    }
}
