# Milestone 13: Singleton IdGenerator

## What We Built

We added:

- `IdGenerator`
- `LazyIdGeneratorExample`

`Main` now uses `IdGenerator` instead of hardcoded IDs.

## Why We Built This

Patients, doctors, appointments, and bills all need unique IDs. If each class or service invented IDs independently, duplicate IDs would become easy.

`IdGenerator` gives ID generation one clear home.

## Java Concepts

- Singleton: one shared instance of a class.
- private constructor: prevents external object creation.
- static instance: stores the single shared object.
- `AtomicInteger`: thread-safe counter.
- formatting strings with `String.format`.

## SOLID Connection

This supports the Single Responsibility Principle because `IdGenerator` only generates IDs.

It also avoids scattering ID rules across entities, services, and `Main`.

## What If We Did Not Use It?

We would keep hardcoding IDs:

```java
"P-001"
"D-001"
"A-001"
```

That is error-prone. Two records could accidentally get the same ID.

## Why Singleton Fits Here

ID generation is app-wide shared state. Having one generator helps keep counters consistent.

## Tradeoff

Singleton is global state. It can make testing harder if overused.

That is why `IdGenerator` must stay narrow. It should not validate patients, save files, or create appointments.

## Eager vs Lazy Singleton

`IdGenerator` uses eager initialization:

```java
private static final IdGenerator INSTANCE = new IdGenerator();
```

The instance is created when the class is initialized.

`LazyIdGeneratorExample` shows lazy initialization:

```java
if (instance == null) {
    instance = new LazyIdGeneratorExample();
}
```

The instance is created only when `getInstance()` is first called.

## Exercise

Why would it be a bad idea for `IdGenerator` to also save data to CSV?
