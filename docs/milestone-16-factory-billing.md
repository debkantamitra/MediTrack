# Milestone 16: Factory Pattern For Billing Strategies

## What We Built

We added `BillingStrategyFactory`.

`Main` now reads the user's billing choice and asks the factory to create the matching `BillingStrategy`.

## Why We Built This

The console UI should not know how every concrete billing strategy is created.

Before the factory, `Main` directly created:

- `StandardBillingStrategy`
- `EmergencyBillingStrategy`
- `FollowUpBillingStrategy`

Now creation is centralized in one class.

## Java Concepts

- static factory method
- private constructor for utility-style factory
- returning an interface type
- switch-based object creation

## SOLID Connection

This supports the Single Responsibility Principle.

- `Main` reads user input.
- `BillingStrategyFactory` creates strategy objects.
- each strategy calculates one billing rule.

It also reduces coupling because `Main` no longer imports every concrete strategy class.

## What If We Did Not Use Factory?

Every place that needs a billing strategy might duplicate this:

```java
switch (choice) {
    case 1:
        return new StandardBillingStrategy();
    case 2:
        return new EmergencyBillingStrategy();
    case 3:
        return new FollowUpBillingStrategy();
}
```

If construction changes later, every duplicate switch must be updated.

## Tradeoff

This is a simple factory, not a full Factory Method hierarchy. That is enough here because we only need one place that maps menu choices to billing strategies.

## Exercise

Why should `BillingStrategyFactory.create()` return `BillingStrategy` instead of `StandardBillingStrategy`?
