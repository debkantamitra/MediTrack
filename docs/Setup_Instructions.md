# Setup Instructions

## Goal

Set up Java, confirm the JDK is available, and run the first MediTrack program.

## Why This Step Matters

Java source code is written in `.java` files, compiled into `.class` bytecode by the JDK, and then executed by the JVM. This is the base workflow behind every Java application we will build in MediTrack.

## JDK, JRE, And JVM

- JDK: Java Development Kit. It includes tools like `javac` for compiling Java code.
- JRE: Java Runtime Environment. It provides what is needed to run Java programs.
- JVM: Java Virtual Machine. It executes compiled Java bytecode.

Modern JDK installations include the runtime pieces needed to run Java programs.

## Verify Java Installation

Run:

```bash
java -version
javac -version
```

Expected result:

- `java -version` prints the installed Java runtime version.
- `javac -version` prints the installed Java compiler version.

Current verified setup:

```text
openjdk version "26.0.1" 2026-04-21
javac 26.0.1
```

## Compile And Run

From the project root, compile:

```bash
javac -d out src/com/airtribe/meditrack/Main.java
```

Then run:

```bash
java -cp out com.airtribe.meditrack.Main
```

Expected output:

```text
Welcome to MediTrack
Milestone 1: Java setup and first runnable app
```

## Concept Learned

The JVM does not run `.java` source files directly in the standard compiled workflow. First, `javac` compiles source code into bytecode. Then, `java` starts the JVM and runs the selected class.

## Design Note

We placed `Main` inside the package `com.airtribe.meditrack`.

This keeps the app organized from the beginning. If we left every class in the default package, the project would become harder to structure once we add `entity`, `service`, `util`, `exception`, and other packages.
