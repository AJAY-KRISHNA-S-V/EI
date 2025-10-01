package decorator;

import util.LoggingUtil;
import java.util.logging.Logger;

public class BasicReport implements Report {
    private static final Logger logger = LoggingUtil.getLogger(BasicReport.class);
    private final String patientName;
    private final String details;

    public BasicReport(String patientName, String details) {
        this.patientName = patientName;
        this.details = details;
        logger.info("BasicReport created for patient=" + patientName);
    }

    @Override
    public String generate() {
        String result = "Report for " + patientName + ": " + details;
        logger.info("BasicReport generated: " + result);
        return result;
    }
}
