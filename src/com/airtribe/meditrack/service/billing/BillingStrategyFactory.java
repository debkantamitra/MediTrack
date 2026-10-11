package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.exception.InvalidDataException;

public final class BillingStrategyFactory {
    private BillingStrategyFactory() {
    }

    public static BillingStrategy create(int choice) {
        switch (choice) {
            case 1:
                return new StandardBillingStrategy();
            case 2:
                return new EmergencyBillingStrategy();
            case 3:
                return new FollowUpBillingStrategy();
            default:
                throw new InvalidDataException("Invalid billing type.");
        }
    }
}
