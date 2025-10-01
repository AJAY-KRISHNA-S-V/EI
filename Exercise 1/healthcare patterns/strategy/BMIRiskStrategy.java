package strategy;

import util.LoggingUtil;
import java.util.logging.Logger;

public class BMIRiskStrategy implements RiskStrategy {
    private static final Logger logger = LoggingUtil.getLogger(BMIRiskStrategy.class);

    @Override
    public int calculateRisk(int bmi) {
        int score = (bmi > 25) ? 70 : 30;
        logger.info("BMI=" + bmi + " → risk score=" + score);
        return score;
    }
}
