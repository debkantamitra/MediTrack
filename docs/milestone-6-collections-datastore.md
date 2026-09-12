# Milestone 6: Collections And Generic DataStore

## What We Built

We added `DataStore<T>`, a reusable in-memory storage class for MediTrack entities.

## Why We Built This

Patients, doctors, and appointments all need basic storage behavior:

- save a record
- find by ID
- list all records
- delete by ID
- count records

Instead of repeating this logic in every service, `DataStore<T>` gives us one reusable storage tool.

## Java Concepts

- generics: `T` lets a class work with different types while keeping type safety.
- bounded generics: `T extends MedicalEntity` means the store only accepts medical entities.
- `HashMap`: stores records by ID for fast lookup.
- `ArrayList`: returns a simple list of records.

## SOLID Connection

This supports the Single Responsibility Principle because storage behavior lives in `DataStore`.

It also helps future Open/Closed design. We can reuse the store for new entity types without rewriting the storage logic.

## What If We Did Not Use This?

Each service might create its own separate `HashMap` logic. That would duplicate add, find, list, delete, and count behavior.

If we later changed storage behavior, we would have to update many places.

## Tradeoff

`DataStore<T>` is intentionally simple. It does not yet handle persistence, sorting, duplicate warnings, or advanced queries. Those belong in later milestones.

## Design Pattern

No design pattern is needed here. This is a generic utility class using Java collections.

## Exercise

Why does `DataStore<T>` use `T extends MedicalEntity` instead of plain `T`?
