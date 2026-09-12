# Milestone 9: Appointment Service

## What We Built

We added:

- `AppointmentService`
- `EntityNotFoundException`
- create, view, search by status, and cancel appointment behavior

## Why We Built This

An appointment connects a doctor, a patient, a date, and a status. That coordination does not belong inside `Doctor`, `Patient`, or `Main`.

`AppointmentService` gives appointment workflows their own home.

## Java Concepts

- object relationships: `Appointment` has a `Doctor` and a `Patient`.
- generic not-found exception: `EntityNotFoundException`.
- service methods: create, find, list, cancel.
- enum-based status search.
- interface implementation with `Searchable<Appointment, AppointmentStatus>`.

## SOLID Connection

This supports the Single Responsibility Principle.

- `Appointment` represents appointment data and state.
- `AppointmentService` manages appointment workflows.
- `DataStore` handles storage.
- `Main` demonstrates app flow.

It also supports Interface Segregation because `Searchable` remains focused only on search behavior.

## What If We Did Not Use AppointmentService?

`Main` would need to know how to create appointments, store them, find them, cancel them, and search by status. That would mix UI/demo code with business workflow.

## Tradeoff

The service adds another class, but it keeps appointment logic testable and separate from patient or doctor logic.

The generic not-found exception reduces duplication across patients, doctors, appointments, and future entities.

## Design Pattern

No design pattern is needed yet. This is service-layer design.

## Exercise

Why is a missing appointment better represented by `EntityNotFoundException` than `InvalidDataException`?
