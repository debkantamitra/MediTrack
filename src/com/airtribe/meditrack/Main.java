package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.util.Validator;

public class Main {
    public static void main(String[] args) {
        String patientName = "Asha";
        int patientAge = 29;
        double consultationFee = 500.75;
        int roundedFee = (int) consultationFee;

        System.out.println("Welcome to " + Constants.APP_NAME);
        System.out.println("Milestone 3: abstract base entities");
        System.out.println("Patient name valid: " + Validator.isValidName(patientName));
        System.out.println("Patient age valid: " + Validator.isValidAge(patientAge));
        System.out.println("Consultation fee: " + consultationFee);
        System.out.println("Rounded fee after casting double to int: " + roundedFee);
        System.out.println("Tax rate: " + Constants.TAX_RATE);
        System.out.println(Constants.CONFIG_STATUS);

        MedicalEntity sampleRecord = new MedicalEntity("ME-001") {
            @Override
            public String getDisplayName() {
                return "Sample medical record";
            }
        };

        System.out.println("Record ID: " + sampleRecord.getId());
        System.out.println("Record name: " + sampleRecord.getDisplayName());
        System.out.println("Record active: " + sampleRecord.isActive());
    }
}
