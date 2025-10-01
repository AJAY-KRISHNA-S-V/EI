package factory;

public class MedicalRecordFactory {

    // Create Doctor or Appointment with just a name
    public static Person create(String type, String name) {
        switch (type) {
            case "Doctor":
                return new Doctor(name);
            case "Appointment":
                return new Appointment(name);
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    // ✅ Overloaded factory method for Patient with full details
    public static Patient createPatient(String name, int age, int bmi, int bp) {
        return new Patient(name, age, bmi, bp);
    }
}
