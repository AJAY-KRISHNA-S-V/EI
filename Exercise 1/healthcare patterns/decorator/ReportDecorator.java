package decorator;

public abstract class ReportDecorator implements Report {
    protected final Report decoratedReport;

    public ReportDecorator(Report decoratedReport) {
        this.decoratedReport = decoratedReport;
    }

    @Override
    public String generate() {
        return decoratedReport.generate();
    }
}
