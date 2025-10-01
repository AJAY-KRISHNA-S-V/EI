package observer;

import util.LoggingUtil;
import java.util.logging.Logger;

public class ObserverDemo {
    private static final Logger logger = LoggingUtil.getLogger(ObserverDemo.class);

    public static void run() {
        logger.info("--- Observer Pattern Demo ---");

        PatientMonitor monitor = new PatientMonitor();

        // Use concrete implementations, not the interface directly
        DoctorObserver doc1 = new Cardiologist("Dr. Singh");
        DoctorObserver doc2 = new Cardiologist("Dr. Rao");

        monitor.addObserver(doc1);
        monitor.addObserver(doc2);

        monitor.setHeartRate(80);
        monitor.setHeartRate(140);
    }
}
