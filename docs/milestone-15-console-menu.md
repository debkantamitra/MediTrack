# Milestone 15: Menu-Driven Console UI

## What We Built

We replaced the demo script in `Main` with a console menu.

The menu can:

- add patients
- view patients
- add doctors
- view doctors
- create appointments
- view appointments
- cancel appointments
- generate bills
- save CSV data

## Why We Built This

Until now, `Main` only demonstrated features with hardcoded data. A console menu turns MediTrack into an interactive app.

## Java Concepts

- `Scanner` for reading user input
- `while` loop for keeping the app running
- `switch` for menu choices
- helper methods for readable flow
- parsing strings into `int`, `double`, and enum values
- `try/catch` at the application boundary

## SOLID Connection

This supports the Single Responsibility Principle.

- `Main` handles console input/output.
- services perform app operations.
- entities protect valid state.
- utilities handle CSV and ID generation.

## Why Catch Exceptions In Main?

Services and entities throw exceptions when something is wrong. `Main` is the boundary closest to the user, so it can turn those exceptions into friendly messages and keep the app running.

## What If Main Did Everything?

If `Main` directly managed collections, validation, billing formulas, and CSV parsing, every feature would be tangled together. The menu would become difficult to maintain.

## Tradeoff

`Main` is now longer because console apps naturally contain input/output flow. Later, we could split the menu into a separate `ConsoleUI` class if it grows too much.

## Design Pattern

No new design pattern is needed. The menu uses the services and the existing Strategy Pattern for billing.

## Exercise

Why should `Main` catch exceptions instead of each entity printing error messages directly?
