# Milestone 7: CRUD Services

## What We Built

We added:

- `PatientService`
- `DoctorService`

Each service uses `DataStore<T>` internally.

## Why We Built This

`Main` should not manage patient and doctor storage directly. As the app grows, `Main` will become the console UI. If it also knows how data is stored, updated, deleted, and counted, it will have too many reasons to change.

Services give patient and doctor workflows a dedicated home.

## Java Concepts

- composition: services contain a `DataStore`.
- generics in use: `DataStore<Patient>` and `DataStore<Doctor>`.
- `List<T>` return types.
- private helper methods.
- CRUD: create, read, update, delete.

## SOLID Connection

This supports the Single Responsibility Principle.

- `Main` demonstrates or later collects user input.
- `PatientService` handles patient operations.
- `DoctorService` handles doctor operations.
- `DataStore` handles reusable storage behavior.

## What If We Did Not Use Services?

`Main` would directly call `HashMap` or `DataStore` everywhere. Later, when we add menus, file loading, searching, billing, and appointments, `Main` would become difficult to read and test.

## Tradeoff

Services add more classes. For a tiny program that can feel like extra work, but it keeps the project ready for real app behavior.

## Design Pattern

No formal design pattern is needed yet. This is service-layer organization using composition.

## Exercise

Why should `PatientService` contain `DataStore<Patient>` instead of extending `DataStore<Patient>`?
