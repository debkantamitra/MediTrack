package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.Validator;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String patientName = "Asha";
        int patientAge = 29;
        double consultationFee = 500.75;
        int roundedFee = (int) consultationFee;

        System.out.println("Welcome to " + Constants.APP_NAME);
        System.out.println("Milestone 12: file I/O and command-line loading");
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
        AppointmentService appointmentService = new AppointmentService();

        if (hasArgument(args, "--loadData")) {
            loadSavedData(patientService, doctorService);
        }

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

        Appointment appointment = appointmentService.createAppointment("A-001", doctor, patient, new Date());
        System.out.println("Appointments stored: " + appointmentService.countAppointments());
        System.out.println("Created appointment: " + appointment.getDisplayName());

        appointmentService.cancelAppointment("A-001");
        System.out.println("Cancelled appointments: " + appointmentService.search(AppointmentStatus.CANCELLED).size());

        Bill bill = new Bill("B-001", appointment, 800);
        BillSummary billSummary = bill.generateBill();
        System.out.println("Generated bill: " + billSummary.getDisplayText());

        Appointment clonedAppointment = appointment.clone();
        patient.setName("Asha Updated");

        System.out.println("Original appointment patient: " + appointment.getPatient().getName());
        System.out.println("Cloned appointment patient: " + clonedAppointment.getPatient().getName());

        saveCurrentData(patientService, doctorService);
    }

    private static boolean hasArgument(String[] args, String expectedArgument) {
        for (String arg : args) {
            if (expectedArgument.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private static void loadSavedData(PatientService patientService, DoctorService doctorService) {
        try {
            List<Patient> patients = CSVUtil.loadPatients(Constants.PATIENTS_CSV_PATH);
            List<Doctor> doctors = CSVUtil.loadDoctors(Constants.DOCTORS_CSV_PATH);

            for (Patient savedPatient : patients) {
                patientService.addPatient(savedPatient);
            }

            for (Doctor savedDoctor : doctors) {
                doctorService.addDoctor(savedDoctor);
            }

            System.out.println("Loaded patients from CSV: " + patients.size());
            System.out.println("Loaded doctors from CSV: " + doctors.size());
        } catch (IOException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
        }
    }

    private static void saveCurrentData(PatientService patientService, DoctorService doctorService) {
        try {
            CSVUtil.savePatients(patientService.findAllPatients(), Constants.PATIENTS_CSV_PATH);
            CSVUtil.saveDoctors(doctorService.findAllDoctors(), Constants.DOCTORS_CSV_PATH);
            System.out.println("Saved patients and doctors to CSV.");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }
}
