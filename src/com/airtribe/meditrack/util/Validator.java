package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Constants;

public final class Validator {
    private Validator() {
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidAge(int age) {
        return age >= Constants.MIN_PATIENT_AGE && age <= Constants.MAX_PATIENT_AGE;
    }

    public static boolean isNonNegative(double value) {
        return value >= 0;
    }

    public static boolean isPresent(Object value) {
        return value == null;
    }
}
