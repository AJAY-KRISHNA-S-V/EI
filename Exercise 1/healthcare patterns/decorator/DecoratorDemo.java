package decorator;


import util.LoggingUtil;
import java.util.logging.Logger;

public class DecoratorDemo {
    private static final Logger logger = LoggingUtil.getLogger(DecoratorDemo.class);

    public static void run() {
        logger.info("--- Decorator Pattern Demo ---");

        Report report = new BasicReport("Alice", "BP Normal");
        logger.info("Basic Report: " + report.generate());

        Report decorated = new EmergencyAlertReport(report, "High heart rate detected");
        logger.info("Decorated Report: " + decorated.generate());

        Report signed = new SignedReportDecorator(decorated, "Dr. Kumar");
        logger.info("Signed & Emergency Report: " + signed.generate());
    }
}

