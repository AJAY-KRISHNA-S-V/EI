package strategy;

import util.LoggingUtil;
import java.util.logging.Logger;

public class BloodPressureRiskStrategy implements RiskStrategy {
    private static final Logger logger = LoggingUtil.getLogger(BloodPressureRiskStrategy.class);

    @Override
    public int calculateRisk(int bp) {
        int score = (bp > 140) ? 80 : 30;
        logger.info("BP=" + bp + " → risk score=" + score);
        return score;
    }
}
