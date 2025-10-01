package observer;

import util.LoggingUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PatientMonitor {
    private static final Logger logger = LoggingUtil.getLogger(PatientMonitor.class);
    private final List<DoctorObserver> observers = new ArrayList<>();
    private int heartRate;

    public void addObserver(DoctorObserver observer) {
        observers.add(observer);
        logger.info("Doctor added to monitor: " + observer);
    }

    public void removeObserver(DoctorObserver observer) {
        observers.remove(observer);
        logger.info("Doctor removed from monitor: " + observer);
    }

    public void setHeartRate(int newRate) {
        this.heartRate = newRate;
        logger.info("Heart rate updated to: " + newRate);
        notifyObservers();
    }

    private void notifyObservers() {
        for (DoctorObserver observer : observers) {
            observer.update(heartRate);
        }
    }
}
