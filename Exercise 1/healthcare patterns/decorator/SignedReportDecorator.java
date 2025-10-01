package decorator;

import util.LoggingUtil;
import java.util.logging.Logger;

public class SignedReportDecorator extends ReportDecorator {
    private static final Logger logger = LoggingUtil.getLogger(SignedReportDecorator.class);
    private final String doctorName;

    public SignedReportDecorator(Report decoratedReport, String doctorName) {
        super(decoratedReport);
        this.doctorName = doctorName;
        logger.info("SignedReportDecorator created by " + doctorName);
    }

    @Override
    public String generate() {
        String base = super.generate();
        String result = base + " | Signed by: " + doctorName;
        logger.info("Signed report generated: " + result);
        return result;
    }
}
