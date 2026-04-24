import java.util.List;

public class Analyzer {
    public double calculateMetricScore(Metric metric){
        double score = 0;
        double normalizedValue = 0;
        normalizedValue = (metric.getValue() - metric.getMinValue())/(metric.getMaxValue() - metric.getMinValue());
        if(metric.getDirection() == Direction.HIGHER){
            score = 1+normalizedValue*4;
        }
        else if(metric.getDirection() == Direction.LOWER){
            score = 5-normalizedValue*4;
        }
        if (score<1){
            score = 1;
        }
        if(score>5){
            score=5;
        }
        score = Math.round(score * 2) / 2.0;

        metric.setScore(score);
        return score;
    }

    public double calculateDimensionScore(Dimension dimension){

    }

    public Dimension findLowestDimension(List<Dimension> dimensions){

    }
}
