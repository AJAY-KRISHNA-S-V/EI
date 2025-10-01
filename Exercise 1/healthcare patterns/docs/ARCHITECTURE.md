# Architecture Walkthrough - HealthcarePatterns

This project demonstrates **6 design patterns** using a Healthcare AI theme.

## Package structure
observer/ -> Observer pattern (Patient monitoring & doctor alerts)
strategy/ -> Strategy pattern (Different health risk calculators)
singleton/ -> Singleton pattern (Central Hospital Database)
factory/ -> Factory pattern (Create Patients, Doctors, Appointments)
adapter/ -> Adapter pattern (Wearable device data integration)
decorator/ -> Decorator pattern (Extend patient reports dynamically)
app/ -> Application runner CLI
util/ -> Logging + Retry utilities
docs/ -> Documentation

## Design Patterns Used
### Behavioral
1. **Observer**
   - Use case: PatientMonitor notifies doctors when heart rate changes.
   - Doctors receive updates dynamically.
   - Future scope: integrate real IoT sensors, doctor dashboards.

2. **Strategy**
   - Use case: Risk calculators (BMI-based, Blood-pressure-based).
   - Allows runtime selection of risk strategy.
   - Future scope: add diabetes risk, cholesterol risk.

### Creational
3. **Singleton**
   - Use case: Central HospitalDatabase (shared across app).
   - Ensures only one instance of DB connection exists.

4. **Factory**
   - Use case: MedicalRecordFactory creates Patients, Doctors, Appointments.
   - Decouples object creation from usage.

### Structural
5. **Adapter**
   - Use case: Wearable device → system data.
   - SmartWatchAdapter converts raw pulse readings into standard DeviceData.

6. **Decorator**
   - Use case: Extend patient reports with emergency alerts and doctor signatures.
   - Demonstrates flexible, dynamic behavior without modifying base class.

## Logging & Reliability
- `util.LoggingUtil` → structured logging (console + rotating file logs).
- `util.RetryUtil` → handles transient errors with exponential backoff.

## Long-running Application
- `app.ApplicationRunner`:
  - CLI-based interactive loop (status, run demos, retry-demo, exit).
  - Background scheduled tasks.
  - Uses `AtomicBoolean` (not while(true)) for loop control.

## Key Decisions
- **Future scope chosen**: Healthcare + IoT is high-growth.
- **Java chosen**: interview required Java/C#/TS → Java picked for portability.
- **Clean package structure**: one class per file, professional layout.
- **Logging**: mandatory for production-grade apps.
- **CLI design**: extensible, long-running, interactive.

## Defensive Programming
- Validations in constructors.
- Retry for transient failures.
- Graceful shutdown of background tasks.

## Interview Walkthrough Tip
- Start at `Main.java` → `ApplicationRunner`.
- Show `help`, `run 1`, `retry-demo`.
- Then explain packages one by one with diagrams.
- Emphasize extensibility & future scope (IoT devices, AI risk models).
