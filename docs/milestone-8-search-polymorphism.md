# Milestone 8: Search And Polymorphism

## What We Built

We added `Searchable<T, C>` and implemented search behavior in `PatientService` and `DoctorService`.

## Why We Built This

Search is a service-level workflow. Entities hold data, and services know how to search stored entities.

## Java Concepts

- interface: defines behavior a class promises to provide.
- generic interface: `Searchable<T, C>` works with a result type and a criteria type.
- default method: `containsIgnoreCase` gives reusable behavior to implementing classes.
- method overloading: `searchPatient(String id)` and `searchPatient(int age)` use the same method name with different parameter types.
- polymorphism: code can call `search(...)` on different services through the same interface shape.

## SOLID Connection

This supports the Interface Segregation Principle because `Searchable` is small and focused.

It also supports Single Responsibility Principle because search logic stays in services, not in `Main` or entity classes.

## What If We Did Not Use This?

Search code would likely be repeated in `Main`, making the UI responsible for scanning collections and comparing fields.

## Important Java Limitation

Java cannot overload methods by parameter name, only by parameter type.

So this is not allowed:

```java
searchPatient(String id)
searchPatient(String name)
```

Both are just `searchPatient(String)` to the compiler. That is why ID lookup uses `searchPatient(String id)`, age uses `searchPatient(int age)`, and name search uses `searchPatientsByName(String name)`.

## Tradeoff

The current implementation uses simple loops. Later, we can refactor search and analytics to streams and lambdas when we reach the Java 8+ milestone.

## Design Pattern

No design pattern is needed yet. This is interface-based polymorphism.

## Exercise

Why is `Searchable` an interface instead of an abstract class?
