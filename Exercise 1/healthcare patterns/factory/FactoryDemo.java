package factory;



import util.LoggingUtil;
import java.util.logging.Logger;

public class FactoryDemo {
    private static final Logger logger = LoggingUtil.getLogger(FactoryDemo.class);

    public static void run() {
        logger.info("--- Factory Pattern Demo ---");

        Person patient = MedicalRecordFactory.create("Patient", "Alice");
        logger.info("Created Patient: " + patient.getName() + " (" + patient.getType() + ")");

        Person doctor = MedicalRecordFactory.create("Doctor", "Dr. Kumar");
        logger.info("Created Doctor: " + doctor.getName() + " (" + doctor.getType() + ")");

        Person appointment = MedicalRecordFactory.create("Appointment", "Appt-001");
        logger.info("Created Appointment: " + appointment.getName() + " (" + appointment.getType() + ")");
    }
}

