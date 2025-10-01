package test;

import strategy.BMIRiskStrategy;
import strategy.BloodPressureRiskStrategy;
import strategy.RiskCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
    void testBMIRiskStrategy() {
        RiskCalculator calc = new RiskCalculator(new BMIRiskStrategy());
        assertEquals(30, calc.calculateRisk(23));
        assertEquals(70, calc.calculateRisk(28));
    }

    @Test
    void testBloodPressureRiskStrategy() {
        RiskCalculator calc = new RiskCalculator(new BloodPressureRiskStrategy());
        assertEquals(30, calc.calculateRisk(120));
        assertEquals(80, calc.calculateRisk(160));
    }
}
