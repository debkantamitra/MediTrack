package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.BillSummary;

public interface BillingStrategy {
    BillSummary generateSummary(String billId, Appointment appointment, double consultationFee);
}
