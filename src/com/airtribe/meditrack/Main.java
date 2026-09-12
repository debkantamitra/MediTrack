package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.Validator;

public class Main {
    public static void main(String[] args) {
        String patientName = "Asha";
        int patientAge = 29;
        double consultationFee = 500.75;
        int roundedFee = (int) consultationFee;

        System.out.println("Welcome to " + Constants.APP_NAME);
        System.out.println("Milestone 8: search and polymorphism");
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

        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();

        Patient patient = new Patient("P-001", "Asha", 29);
        Patient secondPatient = new Patient("P-002", "Asha Kapoor", 35);
        Doctor doctor = new Doctor("D-001", "Dr. Rao", Specialization.CARDIOLOGY, 12);
        Doctor secondDoctor = new Doctor("D-002", "Dr. Sen", Specialization.DERMATOLOGY, 7);

        patientService.addPatient(patient);
        patientService.addPatient(secondPatient);
        doctorService.addDoctor(doctor);
        doctorService.addDoctor(secondDoctor);

        System.out.println("Patients stored: " + patientService.countPatients());
        System.out.println("Doctors stored: " + doctorService.countDoctors());
        System.out.println("Found patient: " + patientService.findPatientById("P-001").getDisplayName());
        System.out.println("Found doctor: " + doctorService.findDoctorById("D-001").getDisplayName());
        System.out.println("Patients named Asha: " + patientService.search("Asha").size());
        System.out.println("Patients aged 35: " + patientService.searchPatient(35).size());
        System.out.println("Cardiology doctors: " + doctorService.search(Specialization.CARDIOLOGY).size());
    }
}
