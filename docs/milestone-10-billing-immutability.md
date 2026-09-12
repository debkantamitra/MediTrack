# Milestone 10: Billing And Immutability

## What We Built

We added:

- `Payable`
- `Bill`
- `BillSummary`

## Why We Built This

Billing is a separate responsibility from appointments. An appointment records a medical visit. A bill calculates and describes payment for that visit.

## Java Concepts

- interface implementation with `Payable`.
- method overriding with `generateBill()`.
- immutable class with `BillSummary`.
- final class and final fields.
- calculation using constants.

## SOLID Connection

This supports the Single Responsibility Principle.

- `Appointment` manages appointment state.
- `Bill` calculates billing details.
- `BillSummary` represents the final billing result.
- `Constants` stores the tax rate.

This also prepares for Open/Closed Principle. Later, if billing rules vary, we can add Strategy Pattern without changing the basic `BillSummary` result object.

## What If We Put Billing In Appointment?

`Appointment` would have two reasons to change:

- appointment scheduling rules change
- billing/tax rules change

That would violate Single Responsibility Principle.

## Why BillSummary Is Immutable

A generated billing summary should not change after creation. It represents a result at a point in time.

`BillSummary` is immutable because:

- the class is `final`
- fields are `private final`
- there are no setters
- all values are assigned in the constructor

## Tradeoff

Immutable objects require creating a new object if values change. The benefit is that they are safer to share and easier to reason about.

## Design Pattern

No billing Strategy Pattern yet. We only have one billing rule, so adding Strategy now would be extra ceremony.

## Exercise

Why should `BillSummary` have no setters?
