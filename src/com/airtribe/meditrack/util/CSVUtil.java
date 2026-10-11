package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.AdmissionStatus;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class CSVUtil {
    private CSVUtil() {
    }

    public static void savePatients(List<Patient> patients, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        createParentDirectory(path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("id,name,age,admissionStatus");
            writer.newLine();

            for (Patient patient : patients) {
                writer.write(patient.getId()
                        + "," + patient.getName()
                        + "," + patient.getAge()
                        + "," + patient.getAdmissionStatus());
                writer.newLine();
            }
        }
    }

    public static List<Patient> loadPatients(String filePath) throws IOException {
        List<Patient> patients = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return patients;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    patients.add(new Patient(
                            values[0],
                            values[1],
                            Integer.parseInt(values[2]),
                            AdmissionStatus.valueOf(values[3])
                    ));
                }
            }
        }

        return patients;
    }

    public static void saveDoctors(List<Doctor> doctors, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        createParentDirectory(path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("id,name,specialization,experience");
            writer.newLine();

            for (Doctor doctor : doctors) {
                writer.write(doctor.getId()
                        + "," + doctor.getName()
                        + "," + doctor.getSpecialization()
                        + "," + doctor.getExperience());
                writer.newLine();
            }
        }
    }

    public static List<Doctor> loadDoctors(String filePath) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return doctors;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    doctors.add(new Doctor(
                            values[0],
                            values[1],
                            Specialization.valueOf(values[2]),
                            Double.parseDouble(values[3])
                    ));
                }
            }
        }

        return doctors;
    }

    private static void createParentDirectory(Path path) throws IOException {
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
    }
}
