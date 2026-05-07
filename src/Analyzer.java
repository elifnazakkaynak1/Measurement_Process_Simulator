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
        double weightedSum = 0.0;
        int totalCoefficient = 0;

        for (Metric metric : dimension.getMetrics()) {
            calculateMetricScore(metric);
            weightedSum += metric.getScore() * metric.getCoefficient();
            totalCoefficient += metric.getCoefficient();
        }

        if (totalCoefficient == 0){
            return 0;
        }

        dimension.setScore(weightedSum/totalCoefficient);
        return weightedSum / totalCoefficient;
    }

    public Dimension findLowestDimension(List<Dimension> dimensions){
        if (dimensions.isEmpty()){
            return null;
        }
        else {
            Dimension lowestDimension = dimensions.get(0);
            for (Dimension dimension : dimensions) {
                if (dimension.getScore() < lowestDimension.getScore()) {
                    lowestDimension = dimension;
                }
            }
            return lowestDimension;
        }
    }

    public double gapAnalysis(Dimension dimension){
        double gap = 0;
        gap = 5.0 - dimension.getScore();
        return gap;
    }

    public String qualityLabel(double score){
        if (score>=4.5){
            return "Excellent";
        }
        else if (score>=3.0){
            return "Good";
        }
        else if (score>=2.0){
            return "Needs Improvement";
        }
        else {
            return "Poor";
        }
    }

}
