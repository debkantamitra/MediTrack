# Milestone 11: Cloning And Deep Copy

## What We Built

We added clone support to:

- `Patient`
- `Appointment`

## Why We Built This

Sometimes an app needs a copy of an object so it can experiment, compare, or keep a snapshot without changing the original object.

## Java Concepts

- `Cloneable`: marker interface showing a class supports cloning.
- `clone()`: method that returns a copy of an object.
- shallow copy: copies the object but shares nested object references.
- deep copy: copies the object and also copies nested mutable objects.

## SOLID Connection

This supports encapsulation. A caller can request a copy instead of directly sharing and mutating the same object.

It also supports Single Responsibility Principle because each class decides how to copy its own internal state.

## What If We Used Only Shallow Copy?

If an appointment clone shared the same patient object as the original, this would be dangerous:

```java
Appointment clonedAppointment = appointment.clone();
patient.setName("Asha Updated");
```

Both the original appointment and cloned appointment would appear to have the updated patient name.

That is not a real snapshot.

## Current Design

`Patient.clone()` creates a new `Patient`.

`Appointment.clone()` creates a new `Appointment` and clones the patient. The date is also copied through the constructor because `Appointment` already uses defensive copying for `Date`.

For now, `Doctor` is shared between the original and cloned appointment because we have not implemented `Doctor.clone()` yet. This is a deliberate small step so we can understand partial vs full deep copy.

## Tradeoff

Deep copying is safer but more work. It can also be expensive for large object graphs.

## Design Pattern

No design pattern is needed here. This is object copying behavior.

## Exercise

Why does `Appointment.clone()` call `patient.clone()` instead of reusing the same `patient` reference?
