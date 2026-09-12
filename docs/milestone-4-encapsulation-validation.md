# Milestone 4: Encapsulation And Validation

## What We Built

We added validation to `MedicalEntity`, `Patient`, `Doctor`, and `Appointment`.

We also added `InvalidDataException`, a custom runtime exception for invalid domain data.

## Why We Built This

Private fields alone do not guarantee valid objects. A class is properly encapsulated only when it controls how its data changes.

## Java Concepts

- constructor validation
- setter validation
- custom exceptions
- throwing exceptions
- reusing utility methods

## SOLID Connection

This supports the Single Responsibility Principle.

- `Patient` protects patient state.
- `Doctor` protects doctor state.
- `Validator` holds reusable validation checks.
- `InvalidDataException` represents invalid application data.
- `AppointmentStatus` limits appointment state to known valid choices.

## What If We Did Not Do This?

Without validation, code like this would be allowed:

```java
new Patient("P1", "", -5);
new Doctor("D1", "", null, -2);
new Appointment("A1", null, null, null);
```

Those objects would exist in an invalid state, and bugs would appear later in services, billing, search, or reports.

## Enum Placement

Enums such as `AdmissionStatus` and `AppointmentStatus` live in the `entity` package for now because they describe domain state used by entities.

They should not go in `constants`. Constants are loose values. Enums are stronger domain types that prevent invalid strings.

## Defensive Copying

`Date` is mutable, so `Appointment` now copies dates when assigning and returning them. This prevents outside code from changing an appointment date without going through the class.

## Tradeoff

Validation adds more code now. The benefit is that invalid data fails early, near the source of the problem.

## Design Pattern

No design pattern is needed here. This is core encapsulation.

## Exercise

Why should validation happen in both constructors and setters?
