# HealthcarePatterns 🚑

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/)
[![Build](https://img.shields.io/badge/build-passing-brightgreen)](#)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)](LICENSE)

A demonstration of **six software design patterns** in a Healthcare AI scenario, built in **Java**.  
This project was prepared for **campus placements (EI)** to showcase coding standards, design thinking, and future-oriented applications.

---

## 📂 Project Structure
HealthcarePatterns/
├── Main.java # Entry point
├── app/ # ApplicationRunner (CLI)
├── util/ # Logging + Retry utilities
├── observer/ # Observer pattern
├── strategy/ # Strategy pattern
├── singleton/ # Singleton pattern
├── factory/ # Factory pattern
├── adapter/ # Adapter pattern
├── decorator/ # Decorator pattern
├── docs/ARCHITECTURE.md # Detailed walkthrough
├── test/ # JUnit tests
├── build_and_run.bat # Compile & run (Windows)
├── .gitignore # Ignore rules
└── README.md # This file


---

## 🧩 Design Patterns Implemented
- **Behavioral**
  - Observer → Patient monitoring alerts
  - Strategy → Dynamic health risk calculators  
- **Creational**
  - Singleton → Central hospital database
  - Factory → Create patients, doctors, appointments  
- **Structural**
  - Adapter → Wearable devices → standardized data
  - Decorator → Extend patient reports with alerts & signatures  

---

## 🛠️ How to Build & Run

### Windows
```bat
build_and_run.bat
javac -encoding UTF-8 app\*.java util\*.java observer\*.java strategy\*.java singleton\*.java factory\*.java adapter\*.java decorator\*.java Main.java
java Main

💻 CLI Usage

When you run the app (java Main), type commands:

Command	Action
help	Show all commands
list	List available demos
run 1..7	Run a demo (1: Observer … 7: RunAll)
retry-demo	Show transient retry mechanism
exit	Stop the application

🧪 Unit Tests

JUnit 5 tests are included for Strategy and Factory.

Run with JUnit Console Launcher:

javac -cp .;lib/junit-platform-console-standalone-1.10.0.jar test/*.java
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path . --scan-class-path

📜 Logging

Logs go to console and logs/app.log

Rotates after 10MB, up to 5 files

Uses java.util.logging via util.LoggingUtil

📖 Documentation

See ARCHITECTURE.md
 for a full walkthrough:

Pattern explanations

Key design decisions

Future scope (IoT + AI in Healthcare)

📌 Key Features

Professional package structure

One class per file (clean code)

Long-running CLI app (no while(true))

Structured logging & retry logic

Defensive programming & validation

📄 License

This project is licensed under the MIT License
.
You’re free to use, modify, and distribute with attribution.

✨ Tip for Interview: Walk the panel through ApplicationRunner, then pick any demo (e.g., Observer). Show how logging flows into logs/app.log. Then explain why Healthcare + IoT is future scope.





