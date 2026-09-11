# Milestone 3: Abstract Base Entities

## What We Built

We added `entity.MedicalEntity`, the abstract parent for important MediTrack records.

## Why We Built This

Patients, doctors, appointments, and bills will all need common record behavior. For now, the common behavior is:

- an ID
- active/inactive status
- a display name method that each child class must define

## Java Concepts

- abstract class: a class that can hold shared code but cannot be directly instantiated by itself.
- private field: data hidden inside the class.
- final field: a field that must be assigned once and cannot be reassigned.
- protected constructor: allows child classes to call the constructor, while discouraging unrelated code from creating base objects.
- abstract method: a method that child classes must implement.

## SOLID Connection

This supports the Single Responsibility Principle because shared entity behavior lives in one base class instead of being repeated across every entity.

It also prepares for Liskov Substitution Principle. Later, code should be able to work with a `MedicalEntity` reference without caring whether the real object is a `Patient`, `Doctor`, or `Appointment`.

## What If We Did Not Do This?

Without a base class, each entity would probably repeat fields like `id` and `active`. Repetition makes changes risky. If we later rename `active` to `enabled`, we would need to update many classes.

## Tradeoff

Inheritance creates a parent-child relationship. That is useful for genuinely shared identity and behavior, but we should avoid putting too much into the base class. A bloated parent class would make every child class carry behavior it may not need.

## No Design Pattern Yet

This milestone uses core OOP, not a design pattern. The important idea is abstraction.

## Exercise

Why should every child entity decide its own `getDisplayName()` instead of `MedicalEntity` returning a fixed string?
