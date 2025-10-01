package strategy;

public class RiskAssessmentContext {
    private RiskCalculator strategy;

    public void setStrategy(RiskCalculator strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int value) {
        if (strategy == null) throw new IllegalStateException("Strategy not set.");
        return strategy.calculateRisk(value);
    }
}
