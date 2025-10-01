package adapter;


import util.LoggingUtil;
import java.util.logging.Logger;

public class AdapterDemo {
    private static final Logger logger = LoggingUtil.getLogger(AdapterDemo.class);

    public static void run() {
        logger.info("--- Adapter Pattern Demo ---");

        WearableDevice watch = new SmartWatch(85);
        DeviceAdapter adapter = new SmartWatchAdapter(watch);
        DeviceData data = adapter.getData();

        logger.info("Adapter converted data: " + data.getFormattedData());
    }
}

