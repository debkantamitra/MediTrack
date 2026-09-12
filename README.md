# MediTrack

MediTrack is a step-by-step Java console application for learning core Java, OOP, SOLID principles, file I/O, and design patterns through a small healthcare management system.

The app currently supports:

- patient creation and listing
- doctor creation and listing
- appointment creation, listing, and cancellation
- billing with multiple billing strategies
- CSV save/load for patients and doctors
- menu-driven console usage

## Learning Focus

This project is intentionally built in milestones. Each feature is used to learn a Java or design concept before moving to the next one.

Concepts covered so far:

- JVM basics, `javac`, `.class` files, and classpath
- packages and imports
- encapsulation with validation
- inheritance and abstract classes
- constructor chaining
- enums for domain modeling
- custom unchecked exceptions
- generics with `DataStore<T>`
- services and composition
- interfaces with `Searchable` and `Payable`
- defensive copying
- immutable `BillSummary`
- CSV file I/O with try-with-resources
- Singleton Pattern with `IdGenerator`
- Strategy Pattern for billing
- Factory Pattern for billing strategy creation
- menu-driven console UI

## Project Structure

```text
src/com/airtribe/meditrack
  Main.java
  constants/
  entity/
  exception/
  interfaces/
  service/
  util/
data/
  patients.csv
  doctors.csv
docs/
  Setup_Instructions.md
```

## Compile

From the project root:

```bash
javac -d out $(find src -name "*.java")
```

This compiles source files from `src` into the `out` directory.

## Run

```bash
java -cp out com.airtribe.meditrack.Main
```

Run and load saved CSV data:

```bash
java -cp out com.airtribe.meditrack.Main --loadData
```

## Optional JVM Property

`Constants` reads a JVM system property for config status.

```bash
java -Dmeditrack.config.status="Loaded from JVM property" -cp out com.airtribe.meditrack.Main
```

In IntelliJ, add this under **Run Configuration > VM options**:

```text
-Dmeditrack.config.status="Loaded from JVM property"
```

## Console Menu

The app menu currently includes:

```text
1. Add patient
2. View patients
3. Add doctor
4. View doctors
5. Create appointment
6. View appointments
7. Cancel appointment
8. Generate bill
9. Save data
0. Save and exit
```

## Design Notes

`Main` handles console input/output and catches user-facing errors.

Services handle workflows:

- `PatientService`
- `DoctorService`
- `AppointmentService`

Entities protect their own state:

- `Patient`
- `Doctor`
- `Appointment`
- `Bill`

Utilities handle reusable technical work:

- `Validator`
- `DataStore<T>`
- `CSVUtil`
- `IdGenerator`

Billing uses Strategy plus Factory:

- `BillingStrategy` defines the calculation contract.
- concrete strategies implement different rules.
- `BillingStrategyFactory` creates the selected strategy from the menu choice.
- `Bill` uses the selected strategy.

## Current Limitations

- CSV parsing uses simple `String.split(",")`, so names with commas are not supported.
- CSV persistence currently covers patients and doctors only.
- appointments and bills are stored only in memory.
- the console UI is still inside `Main`; it may later be split into a dedicated UI class.
- IDs restart when the app restarts.

## Next Possible Steps

- persist appointments and bills
- add a dedicated `ConsoleUI` class
- add manual `TestRunner`
- add streams and lambdas for search/analytics
- add concurrency examples around reminders or ID generation
- add JavaDocs and final project documentation
