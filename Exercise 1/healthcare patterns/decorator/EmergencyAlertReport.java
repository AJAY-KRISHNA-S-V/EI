package decorator;

import util.LoggingUtil;
import java.util.logging.Logger;

public class EmergencyAlertReport extends ReportDecorator {
    private static final Logger logger = LoggingUtil.getLogger(EmergencyAlertReport.class);
    private final String alert;

    public EmergencyAlertReport(Report decoratedReport, String alert) {
        super(decoratedReport);
        this.alert = alert;
        logger.warning("EmergencyAlertReport created with alert=" + alert);
    }

    @Override
    public String generate() {
        String base = super.generate();
        String result = base + " | EMERGENCY: " + alert;
        logger.warning("Emergency report generated: " + result);
        return result;
    }
}
