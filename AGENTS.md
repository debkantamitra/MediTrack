# Agent Instructions

MediTrack is a step-by-step Java learning project. The goal is not only to complete the app, but to help the learner understand why each concept, class, and design choice exists.

## Teaching Flow For Every Step

For every meaningful change, explain:

1. The problem being solved.
2. The Java concept involved.
3. The design choice being made.
4. The relevant SOLID principle, when applicable.
5. What would go wrong without this approach.
6. The tradeoff or cost of the design.
7. The smallest useful implementation.
8. How to verify the behavior.
9. A small exercise or reflection before moving on.

## Development Style

- Prefer clear learning progression over rushing ahead.
- Keep changes small and focused.
- Do not introduce design patterns just to show them.
- Use a design pattern only when it solves a real MediTrack problem.
- Keep examples tied to patients, doctors, appointments, billing, persistence, or reports.
- Explain code in plain language before adding another abstraction.
- Preserve the package base `com.airtribe.meditrack`.

## SOLID Principles To Reinforce

- Single Responsibility Principle: each class should have one clear reason to change.
- Open/Closed Principle: code should be easy to extend without modifying stable logic.
- Liskov Substitution Principle: subclasses should behave safely when used as parent types.
- Interface Segregation Principle: prefer small focused interfaces, such as `Payable` and `Searchable`.
- Dependency Inversion Principle: high-level logic should not depend directly on low-level details when abstraction is useful.

## Design Patterns To Teach Only When Useful

- Singleton: shared configuration or ID generation.
- Factory: creating bill objects without scattering creation logic.
- Strategy: switching billing calculation rules cleanly.
- Template Method: shared workflows such as file import/export, if the code naturally needs it.
- Observer: appointment notifications or reminders, if added.

## Design Questions To Ask

- What responsibility belongs in this class?
- What would become harder if this logic stayed in `Main.java`?
- Is this abstraction helping, or is it extra ceremony?
- What would break if the requirement changed?
- Is this class easy to test manually?

## Rule For Future Work

Do not just provide code. Teach the reason behind the code, name the concept, point out the design principle, and explain the cost of ignoring it.
