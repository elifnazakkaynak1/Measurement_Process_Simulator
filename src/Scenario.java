import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private String name;
    private Mode mode;
    private QualityType qualityType;
    private List<Dimension> dimensions;

    public Scenario(String name, Mode mode, QualityType qualityType, List<Dimension> dimensions) {
        this.name = name;
        this.mode = mode;
        this.qualityType = qualityType;
        this.dimensions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public Mode getMode() {
        return mode;
    }
    public QualityType getQualityType() {
        return qualityType;
    }
    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    public void setQualityType(QualityType qualityType) {
        this.qualityType = qualityType;
    }
    public void addDimension(List<Dimension> dimension) {
        dimensions.add((Dimension) dimension);
    }
}
