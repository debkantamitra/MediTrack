package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();

    private final AtomicInteger patientCounter;
    private final AtomicInteger doctorCounter;
    private final AtomicInteger appointmentCounter;
    private final AtomicInteger billCounter;

    private IdGenerator() {
        this.patientCounter = new AtomicInteger(1);
        this.doctorCounter = new AtomicInteger(1);
        this.appointmentCounter = new AtomicInteger(1);
        this.billCounter = new AtomicInteger(1);
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String nextPatientId() {
        return nextId("P", patientCounter);
    }

    public String nextDoctorId() {
        return nextId("D", doctorCounter);
    }

    public String nextAppointmentId() {
        return nextId("A", appointmentCounter);
    }

    public String nextBillId() {
        return nextId("B", billCounter);
    }

    private String nextId(String prefix, AtomicInteger counter) {
        return prefix + "-" + String.format("%03d", counter.getAndIncrement());
    }
}
