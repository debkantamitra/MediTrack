# Milestone 12: File I/O And Command-Line Loading

## What We Built

We added:

- `CSVUtil`
- CSV saving for patients and doctors
- CSV loading for patients and doctors
- `--loadData` command-line argument support

## Why We Built This

Until now, data existed only in memory. When the program ended, all patients and doctors disappeared.

CSV persistence lets the app save simple data to files and load it again later.

## Java Concepts

- file writing with `BufferedWriter`
- file reading with `BufferedReader`
- `Path`, `Paths`, and `Files`
- `try-with-resources`
- checked exception handling with `IOException`
- command-line arguments using `String[] args`
- simple CSV parsing with `String.split(",")`

## SOLID Connection

This supports the Single Responsibility Principle.

- `CSVUtil` handles CSV file reading and writing.
- services manage app workflows.
- `Main` decides when to load or save based on command-line arguments.

## What If We Put File I/O In Services?

`PatientService` and `DoctorService` would become responsible for both business operations and file format details.

That would make them harder to change later if we moved from CSV to serialization or a database.

## Why Try-With-Resources?

Files must be closed after reading or writing. `try-with-resources` closes them automatically, even if an exception happens.

## Tradeoff

This CSV implementation is intentionally simple. It uses `String.split(",")`, so it does not handle names containing commas.

That is acceptable for this learning milestone because the requirement asks for simple CSV parsing with `split(",")`.

## Design Pattern

No design pattern is needed yet. This is basic persistence using a utility class.

## Exercise

Why is `IOException` a checked exception, while `InvalidDataException` is unchecked?
