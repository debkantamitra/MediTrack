package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.service.billing.BillingStrategy;
import com.airtribe.meditrack.service.billing.BillingStrategyFactory;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.IdGenerator;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final IdGenerator idGenerator;
    private final Scanner scanner;

    public Main() {
        this.patientService = new PatientService();
        this.doctorService = new DoctorService();
        this.appointmentService = new AppointmentService();
        this.idGenerator = IdGenerator.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main app = new Main();

        System.out.println("Welcome to " + Constants.APP_NAME);
        System.out.println("Milestone 16: Factory Pattern for billing strategies");
        System.out.println(Constants.CONFIG_STATUS);

        if (hasArgument(args, "--loadData")) {
            app.loadSavedData();
        }

        app.runMenu();
    }

    private void runMenu() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        listPatients();
                        break;
                    case 3:
                        addDoctor();
                        break;
                    case 4:
                        listDoctors();
                        break;
                    case 5:
                        createAppointment();
                        break;
                    case 6:
                        listAppointments();
                        break;
                    case 7:
                        cancelAppointment();
                        break;
                    case 8:
                        generateBill();
                        break;
                    case 9:
                        saveCurrentData();
                        break;
                    case 0:
                        saveCurrentData();
                        running = false;
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Please choose a valid option.");
                }
            } catch (RuntimeException e) {
                System.out.println("Could not complete action: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add patient");
        System.out.println("2. View patients");
        System.out.println("3. Add doctor");
        System.out.println("4. View doctors");
        System.out.println("5. Create appointment");
        System.out.println("6. View appointments");
        System.out.println("7. Cancel appointment");
        System.out.println("8. Generate bill");
        System.out.println("9. Save data");
        System.out.println("0. Save and exit");
    }

    private void addPatient() {
        String name = readText("Patient name: ");
        int age = readInt("Patient age: ");

        Patient patient = new Patient(idGenerator.nextPatientId(), name, age);
        patientService.addPatient(patient);

        System.out.println("Added patient: " + patient.getDisplayName());
    }

    private void listPatients() {
        List<Patient> patients = patientService.findAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        for (Patient patient : patients) {
            System.out.println(patient.getId() + " - " + patient.getDisplayName());
        }
    }

    private void addDoctor() {
        String name = readText("Doctor name: ");
        Specialization specialization = readSpecialization();
        double experience = readDouble("Experience in years: ");

        Doctor doctor = new Doctor(idGenerator.nextDoctorId(), name, specialization, experience);
        doctorService.addDoctor(doctor);

        System.out.println("Added doctor: " + doctor.getDisplayName());
    }

    private void listDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        for (Doctor doctor : doctors) {
            System.out.println(doctor.getId() + " - " + doctor.getDisplayName());
        }
    }

    private void createAppointment() {
        String doctorId = readText("Doctor ID: ");
        String patientId = readText("Patient ID: ");

        Doctor doctor = doctorService.findDoctorById(doctorId);
        Patient patient = patientService.findPatientById(patientId);

        if (doctor == null) {
            throw new InvalidDataException("Doctor not found for id: " + doctorId);
        }
        if (patient == null) {
            throw new InvalidDataException("Patient not found for id: " + patientId);
        }

        Appointment appointment = appointmentService.createAppointment(
                idGenerator.nextAppointmentId(),
                doctor,
                patient,
                new Date()
        );

        System.out.println("Created appointment: " + appointment.getDisplayName());
    }

    private void listAppointments() {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment.getId() + " - " + appointment.getDisplayName());
        }
    }

    private void cancelAppointment() {
        String appointmentId = readText("Appointment ID: ");
        appointmentService.cancelAppointment(appointmentId);
        System.out.println("Cancelled appointment: " + appointmentId);
    }

    private void generateBill() {
        String appointmentId = readText("Appointment ID: ");
        double consultationFee = readDouble("Consultation fee: ");
        BillingStrategy billingStrategy = readBillingStrategy();

        Appointment appointment = appointmentService.findAppointmentById(appointmentId);
        if (appointment == null) {
            throw new InvalidDataException("Appointment not found for id: " + appointmentId);
        }

        Bill bill = new Bill(idGenerator.nextBillId(), appointment, consultationFee, billingStrategy);
        BillSummary billSummary = bill.generateBill();

        System.out.println("Generated bill: " + billSummary.getDisplayText());
    }

    private Specialization readSpecialization() {
        System.out.println("Available specializations:");
        for (Specialization specialization : Specialization.values()) {
            System.out.println("- " + specialization);
        }

        String value = readText("Specialization: ");
        return Specialization.valueOf(value.trim().toUpperCase());
    }

    private BillingStrategy readBillingStrategy() {
        System.out.println("Billing type:");
        System.out.println("1. Standard");
        System.out.println("2. Emergency");
        System.out.println("3. Follow up");

        int choice = readInt("Choose billing type: ");
        return BillingStrategyFactory.create(choice);
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readText(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readText(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static boolean hasArgument(String[] args, String expectedArgument) {
        for (String arg : args) {
            if (expectedArgument.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private void loadSavedData() {
        try {
            List<Patient> patients = CSVUtil.loadPatients(Constants.PATIENTS_CSV_PATH);
            List<Doctor> doctors = CSVUtil.loadDoctors(Constants.DOCTORS_CSV_PATH);

            for (Patient patient : patients) {
                patientService.addPatient(patient);
            }

            for (Doctor doctor : doctors) {
                doctorService.addDoctor(doctor);
            }

            System.out.println("Loaded patients from CSV: " + patients.size());
            System.out.println("Loaded doctors from CSV: " + doctors.size());
        } catch (IOException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
        }
    }

    private void saveCurrentData() {
        try {
            CSVUtil.savePatients(patientService.findAllPatients(), Constants.PATIENTS_CSV_PATH);
            CSVUtil.saveDoctors(doctorService.findAllDoctors(), Constants.DOCTORS_CSV_PATH);
            System.out.println("Saved patients and doctors to CSV.");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }
}
