package com.airtribe.meditrack.constants;

public final class Constants {
    public static final String APP_NAME = "MediTrack";
    public static final String CONFIG_STATUS;
    public static final double TAX_RATE = 0.18;
    public static final int MIN_PATIENT_AGE = 0;
    public static final int MAX_PATIENT_AGE = 120;

    static {
        CONFIG_STATUS = "Application constants loaded.";
    }

    private Constants() {
    }
}
