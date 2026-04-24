public class Metric {
    private String name;
    private int coefficient;
    private Direction direction;
    private double minValue;
    private double maxValue;
    private String unit;
    private double value;
    private double score;

    public Metric(String name, int coefficient, Direction direction, double minValue, double maxValue, String unit, double value, double score) {
        this.name = name;
        this.coefficient = coefficient;
        this.direction = direction;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
        this.value = value;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public int getCoefficient() {
        return coefficient;
    }
    public Direction getDirection() {
        return direction;
    }
    public double getMinValue() {
        return minValue;
    }
    public double getMaxValue() {
        return maxValue;
    }
    public String getUnit() {
        return unit;
    }
    public double getValue() {
        return value;
    }
    public double getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public void setScore(double score) {
        this.score = score;
    }

}
