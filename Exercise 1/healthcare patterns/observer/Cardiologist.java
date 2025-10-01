package observer;

import util.LoggingUtil;
import java.util.logging.Logger;

public class Cardiologist implements DoctorObserver {
    private static final Logger logger = LoggingUtil.getLogger(Cardiologist.class);
    private final String name;

    public Cardiologist(String name) {
        this.name = name;
    }

    @Override
    public void update(int heartRate) {
        if (heartRate > 120) {
            logger.warning("[ALERT] " + name + ": Patient heart rate is too high: " + heartRate);
        } else {
            logger.info(name + " notified: heart rate is " + heartRate + ". No action required.");
        }
    }

    @Override
    public String toString() {
        return "Cardiologist " + name;
    }
}
