package strategy;



import util.LoggingUtil;
import java.util.logging.Logger;

public class StrategyDemo {
    private static final Logger logger = LoggingUtil.getLogger(StrategyDemo.class);

    public static void run() {
        logger.info("--- Strategy Pattern Demo ---");

        RiskCalculator bmiCalc = new RiskCalculator(new BMIRiskStrategy());
        logger.info("BMI Risk Score (bmi=28): " + bmiCalc.calculateRisk(28));
        logger.info("Context Risk (bmi=23): " + bmiCalc.calculateRisk(23));

        RiskCalculator bpCalc = new RiskCalculator(new BloodPressureRiskStrategy());
        logger.info("Blood Pressure Risk Score (bp=150): " + bpCalc.calculateRisk(150));
        logger.info("Context Risk (bp=160): " + bpCalc.calculateRisk(160));
    }
}

