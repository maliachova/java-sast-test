# Problematic Java Project

This project is intentionally written with bad practices for testing scanners, training, and refactoring exercises.

## What it contains

- Hardcoded secrets
- Unsafe string concatenation
- Command execution from user input
- Weak hashing
- Mutable global state
- Poor exception handling

## Run

```bash
mvn -q package
mvn -q exec:java -Dexec.mainClass=com.example.problematic.ProblematicApp
```

The project does not aim to be secure or production ready.
