package adapter;

import util.LoggingUtil;
import java.util.logging.Logger;

public class SmartWatch implements WearableDevice {
    private static final Logger logger = LoggingUtil.getLogger(SmartWatch.class);
    private final int pulse;

    public SmartWatch(int pulse) {
        this.pulse = pulse;
        logger.info("SmartWatch initialized with pulse=" + pulse);
    }

    @Override
    public int getPulse() {
        logger.info("SmartWatch returning pulse=" + pulse);
        return pulse;
    }
}
