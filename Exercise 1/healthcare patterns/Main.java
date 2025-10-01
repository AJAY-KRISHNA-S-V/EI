import java.util.Scanner;
import java.util.Map;

import factory.Patient;
import factory.MedicalRecordFactory;
import singleton.HospitalDatabase;
import strategy.*;
import observer.*;
import adapter.*;
import decorator.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Healthcare App ===");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Enter new patient data");
            System.out.println("2. View patient record (from DB)");
            System.out.println("3. List all patient records");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input. Please enter a number (1-4).");
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("❌ Name cannot be empty.");
                        break;
                    }

                    int age = getValidInt(sc, "Enter age (1-120): ", 1, 120);
                    int bmi = getValidInt(sc, "Enter BMI (10-60): ", 10, 60);
                    int bp = getValidInt(sc, "Enter Blood Pressure (60-250): ", 60, 250);

                    // ✅ Factory Pattern → Create patient
                    Patient patient = MedicalRecordFactory.createPatient(name, age, bmi, bp);
                    System.out.println("\n[Factory] ✅ Patient created: " + patient);

                    // ✅ Singleton Pattern → Save to DB
                    HospitalDatabase db = HospitalDatabase.getInstance();
                    db.addRecord(patient.getName(), "Age=" + age + ", BMI=" + bmi + ", BP=" + bp);
                    System.out.println("[Singleton] 📁 Record stored in Hospital DB.");

                    // ✅ Strategy Pattern → Risk calculations
                    RiskCalculator calc = new RiskCalculator(new BMIRiskStrategy());
                    System.out.println("[Strategy] BMI Risk Score = " + calc.calculateRisk(patient.getBmi()));
                    calc.setStrategy(new BloodPressureRiskStrategy());
                    System.out.println("[Strategy] BP Risk Score = " + calc.calculateRisk(patient.getBp()));

                    // ✅ Observer Pattern → Doctors monitor patient’s heart rate
                    PatientMonitor monitor = new PatientMonitor();
                    monitor.addObserver(new Cardiologist("Dr. Singh"));
                    monitor.addObserver(new Cardiologist("Dr. Rao"));
                    System.out.println("[Observer] 🔍 Monitoring heart rate...");
                    monitor.setHeartRate(patient.getBp());

                    // ✅ Adapter Pattern → Wearable device integration
                    WearableDevice watch = new SmartWatch(patient.getBp());
                    DeviceAdapter adapter = new SmartWatchAdapter(watch);
                    DeviceData data = adapter.getData();
                    System.out.println("[Adapter] ⌚ Device data converted: " + data.getFormattedData());

                    // ✅ Decorator Pattern → Generate reports
                    Report report = new BasicReport(patient.getName(),
                            "Age=" + age + ", BMI=" + bmi + ", BP=" + bp);
                    if (bp > 140) {
                        report = new EmergencyAlertReport(report, "High blood pressure detected");
                    }
                    report = new SignedReportDecorator(report, "Dr. Kumar");
                    System.out.println("[Decorator] 📝 Final Report: " + report.generate());
                    break;
                }
                case 2: {
                    System.out.print("Enter patient name to search: ");
                    String searchName = sc.nextLine().trim();
                    if (searchName.isEmpty()) {
                        System.out.println("❌ Name cannot be empty.");
                        break;
                    }
                    HospitalDatabase db = HospitalDatabase.getInstance();
                    System.out.println("📂 Record: " + db.getRecord(searchName));
                    break;
                }
                case 3: {
                    HospitalDatabase db = HospitalDatabase.getInstance();
                    Map<String, String> all = db.getAllRecords();
                    if (all.isEmpty()) {
                        System.out.println("📂 No patient records found.");
                    } else {
                        System.out.println("📂 All Patient Records:");
                        all.forEach((k, v) -> System.out.println(" - " + k + " => " + v));
                    }
                    break;
                }
                case 4: {
                    running = false;
                    System.out.println("👋 Exiting Healthcare App...");
                    break;
                }
                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }

        sc.close();
    }

    private static int getValidInt(Scanner sc, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }
            value = sc.nextInt();
            sc.nextLine();
            if (value < min || value > max) {
                System.out.println("❌ Value must be between " + min + " and " + max + ".");
            } else {
                break;
            }
        }
        return value;
    }
}
