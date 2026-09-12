# Milestone 14: Strategy Pattern For Billing

## What We Built

We added billing strategies:

- `BillingStrategy`
- `StandardBillingStrategy`
- `EmergencyBillingStrategy`
- `FollowUpBillingStrategy`

`Bill` now delegates billing calculation to a strategy object.

## Why We Built This

Billing rules can vary. A normal visit, emergency visit, and follow-up visit may calculate totals differently.

If every billing rule lived inside `Bill.generateBill()`, the method would keep growing with conditionals.

## Java Concepts

- interface-based polymorphism
- composition
- constructor overloading
- method overriding
- delegation

## SOLID Connection

This supports the Open/Closed Principle.

`Bill` is now closed for constant editing but open to new billing behavior. We can add a new class such as `InsuranceBillingStrategy` without rewriting `Bill.generateBill()`.

It also supports Single Responsibility Principle.

- `Bill` owns bill data.
- each strategy owns one calculation rule.
- `BillSummary` owns the final immutable result.

## What If We Did Not Use Strategy?

`Bill.generateBill()` might turn into this:

```java
if (type.equals("STANDARD")) {
    ...
} else if (type.equals("EMERGENCY")) {
    ...
} else if (type.equals("FOLLOW_UP")) {
    ...
}
```

That gets harder to test and easier to break as rules grow.

## Tradeoff

Strategy adds more classes. It is worth it only because billing behavior now has real variation.

## Exercise

Why is `Bill` using a `BillingStrategy` field instead of extending `StandardBillingStrategy`?
