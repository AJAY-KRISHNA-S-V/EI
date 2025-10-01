package adapter;

import util.LoggingUtil;
import java.util.logging.Logger;

public class SmartWatchAdapter implements DeviceAdapter {
    private static final Logger logger = LoggingUtil.getLogger(SmartWatchAdapter.class);
    private final WearableDevice device;

    public SmartWatchAdapter(WearableDevice device) {
        this.device = device;
        logger.info("SmartWatchAdapter initialized.");
    }

    @Override
    public DeviceData getData() {
        int pulse = device.getPulse();
        String formatted = "Pulse: " + pulse + " bpm";
        logger.info("Adapter converting pulse=" + pulse + " to formattedData=" + formatted);
        return new DeviceData(formatted);
    }
}
