# Milestone 5: Enums And Stronger Domain Modeling

## What We Built

We replaced doctor specialization strings with a `Specialization` enum.

## Why We Built This

A doctor's specialization should come from a known list. A plain `String` allows typos, inconsistent casing, and unexpected values.

## Java Concepts

- enum: a type with a fixed set of allowed values.
- type safety: the compiler helps prevent invalid values.
- null validation: enum variables can still be `null`, so required enum fields must be checked.

## SOLID Connection

This supports the Single Responsibility Principle because `Specialization` owns the allowed specialization choices.

It also helps future Open/Closed design. Search and filtering logic can use stable enum values instead of fragile string comparisons.

## What If We Did Not Use An Enum?

With strings, these could all mean the same thing but behave differently:

```java
"Cardiology"
"cardiology"
"CARDIO"
"Heart"
```

That would make search, reports, and analytics unreliable.

## Tradeoff

Enums are less flexible than strings. If a new specialization is needed, we must update the enum. For this project, that is a good tradeoff because correctness matters more than accepting arbitrary values.

## Design Pattern

No design pattern is needed here. Enum is a language feature for stronger domain modeling.

## Exercise

Why do we still validate `specialization != null` even though `Specialization` is an enum?
