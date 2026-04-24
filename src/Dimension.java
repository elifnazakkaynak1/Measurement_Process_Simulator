import java.util.ArrayList;
import java.util.List;

public class Dimension {
    private String name;
    private int coefficient;
    private double score;
    private List<Metric> metrics;

    public Dimension(String name, int coefficient, double score) {
        this.name = name;
        this.coefficient = coefficient;
        this.score = score;
        this.metrics = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public int getCoefficient() {
        return coefficient;
    }
    public double getScore() {
        return score;
    }
    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

}
