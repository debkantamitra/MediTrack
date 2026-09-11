# Milestone 2: Package Structure And Java Basics

## What We Built

We added:

- `constants.Constants`
- `util.Validator`
- imports in `Main`
- examples of primitive types and casting

## Why We Built This

As MediTrack grows, putting all logic inside `Main` would make the app hard to read, test, and change. Package structure gives each kind of code a home.

## Java Concepts

- `package`: declares where a class belongs.
- `import`: lets one class use another class from a different package.
- `public`: allows a class or member to be accessed from other packages.
- `static`: belongs to the class, not to an object instance.
- static block: runs when the class is initialized.
- `final`: prevents reassignment for constants and inheritance for utility classes.
- primitive types: examples include `int` and `double`.
- casting: converting one type into another, such as `double` to `int`.

## SOLID Connection

This step supports the Single Responsibility Principle.

- `Main` starts the program and demonstrates flow.
- `Constants` stores shared fixed values.
- `Validator` checks whether data is acceptable.

Each class now has a clearer reason to change.

## What If We Did Not Do This?

If all constants and validation rules stayed in `Main`, every new feature would make `Main` larger. Later, when we add patients, doctors, appointments, billing, and file loading, one file would be responsible for too many things.

That would make bugs easier to introduce and harder to find.

## Tradeoff

This creates more files early. That feels slower at first, but it pays off when the project grows.

## Static Initialization Note

Simple `public static final` constants like strings and numbers can be inlined by the compiler. To demonstrate a static block clearly, `Constants.CONFIG_STATUS` is assigned inside the static block and then read from `Main`.

## No Design Pattern Yet

We are not using a design pattern in this milestone. A pattern here would be extra ceremony. Simple packages and small classes are enough.

## Exercise

Try changing `patientAge` in `Main` to `-5` or `150`. Predict the output before running the program.
