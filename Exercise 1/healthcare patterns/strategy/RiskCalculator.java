package strategy;

public class RiskCalculator {
    private RiskStrategy strategy;

    public RiskCalculator(RiskStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RiskStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateRisk(int value) {
        return strategy.calculateRisk(value);
    }
}
