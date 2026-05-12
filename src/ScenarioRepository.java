import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenarioRepository {
    private static final Map<String, Scenario> ALL = new HashMap<>();

    static {
        ALL.put("Scenario C - Team Alpha", buildEducationScenarioC());
        ALL.put("Scenario D - Team Beta",  buildEducationScenarioD());
        ALL.put("Scenario A - Team Alpha", buildHealthScenarioA());
        ALL.put("Scenario B - Team Beta",  buildHealthScenarioB());
    }

    public static Scenario getScenario(String name) {
        return ALL.get(name);
    }

    public static List<String> getScenariosForMode(Mode mode) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Scenario> entry : ALL.entrySet()) {
            if (entry.getValue().getMode() == mode) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    private static Scenario buildEducationScenarioC() {

        // ---------- Usability (coefficient: 25) ----------
        Dimension usability = new Dimension("Usability", 25, 0.0);
        usability.addMetric(new Metric(
                "SUS Score",       50, Direction.HIGHER, 0, 100,  "points", 89, 0));
        usability.addMetric(new Metric(
                "Onboarding Time", 50, Direction.LOWER,  0,  60,  "min",     5, 0));

        // ---------- Performance Efficiency (coefficient: 20) ----------
        Dimension perfEfficiency = new Dimension("Performance Efficiency", 20, 0.0);
        perfEfficiency.addMetric(new Metric(
                "Video Start Time",   50, Direction.LOWER,  0,  15, "sec",   3, 0));
        perfEfficiency.addMetric(new Metric(
                "Concurrent Exams",   50, Direction.HIGHER, 0, 600, "users", 450, 0));

        // ---------- Accessibility (coefficient: 20) ----------
        Dimension accessibility = new Dimension("Accessibility", 20, 0.0);
        accessibility.addMetric(new Metric(
                "WCAG Compliance",     50, Direction.HIGHER, 0, 100, "%", 88, 0));
        accessibility.addMetric(new Metric(
                "Screen Reader Score", 50, Direction.HIGHER, 0, 100, "%", 76, 0));

        // ---------- Reliability (coefficient: 20) ----------
        Dimension reliability = new Dimension("Reliability", 20, 0.0);
        reliability.addMetric(new Metric(
                "Uptime", 50, Direction.HIGHER, 95, 100, "%",   99.2, 0));
        reliability.addMetric(new Metric(
                "MTTR",   50, Direction.LOWER,   0, 120, "min", 18,   0));

        // ---------- Functional Suitability (coefficient: 15) ----------
        Dimension funcSuitability = new Dimension("Functional Suitability", 15, 0.0);
        funcSuitability.addMetric(new Metric(
                "Feature Completion",   50, Direction.HIGHER, 0, 100, "%", 92, 0));
        funcSuitability.addMetric(new Metric(
                "Assignment Submit Rate", 50, Direction.HIGHER, 0, 100, "%", 85, 0));

        // ---------- Scenario ----------
        Scenario scenario = new Scenario(
                "Scenario C - Team Alpha",
                Mode.EDUCATION,
                QualityType.Product,
                new ArrayList<>()
        );
        scenario.addDimension(usability);
        scenario.addDimension(perfEfficiency);
        scenario.addDimension(accessibility);
        scenario.addDimension(reliability);
        scenario.addDimension(funcSuitability);
        return scenario;
    }

    // ------------------------------------------------------------------ //
    //  EDUCATION — Scenario D  (Process Quality)
    // ------------------------------------------------------------------ //
    private static Scenario buildEducationScenarioD() {

        // ---------- Sprint Efficiency (coefficient: 30) ----------
        Dimension sprintEfficiency = new Dimension("Sprint Efficiency", 30, 0.0);
        sprintEfficiency.addMetric(new Metric(
                "Sprint Velocity",        50, Direction.HIGHER, 0, 100, "points", 72, 0));
        sprintEfficiency.addMetric(new Metric(
                "Sprint Completion Rate", 50, Direction.HIGHER, 0, 100, "%",      81, 0));

        // ---------- Code Quality (coefficient: 25) ----------
        Dimension codeQuality = new Dimension("Code Quality", 25, 0.0);
        codeQuality.addMetric(new Metric(
                "Code Coverage",    50, Direction.HIGHER, 0, 100, "%",    68, 0));
        codeQuality.addMetric(new Metric(
                "Bug Density",      50, Direction.LOWER,  0,  20, "bugs/KLOC", 4, 0));

        // ---------- Team Collaboration (coefficient: 25) ----------
        Dimension teamCollab = new Dimension("Team Collaboration", 25, 0.0);
        teamCollab.addMetric(new Metric(
                "Code Review Rate",   50, Direction.HIGHER, 0, 100, "%",  90, 0));
        teamCollab.addMetric(new Metric(
                "Meeting Attendance", 50, Direction.HIGHER, 0, 100, "%",  85, 0));

        // ---------- Delivery Performance (coefficient: 20) ----------
        Dimension delivery = new Dimension("Delivery Performance", 20, 0.0);
        delivery.addMetric(new Metric(
                "On-Time Delivery Rate", 50, Direction.HIGHER, 0, 100, "%",  78, 0));
        delivery.addMetric(new Metric(
                "Deployment Frequency",  50, Direction.HIGHER, 0,  30, "deploys/month", 12, 0));

        Scenario scenario = new Scenario(
                "Scenario D - Team Beta",
                Mode.EDUCATION,
                QualityType.Process,
                new ArrayList<>()
        );
        scenario.addDimension(sprintEfficiency);
        scenario.addDimension(codeQuality);
        scenario.addDimension(teamCollab);
        scenario.addDimension(delivery);
        return scenario;
    }

    // ------------------------------------------------------------------ //
    //  HEALTH — Scenario A  (Product Quality)
    // ------------------------------------------------------------------ //
    private static Scenario buildHealthScenarioA() {

        // ---------- Usability (coefficient: 30) ----------
        Dimension usability = new Dimension("Usability", 30, 0.0);
        usability.addMetric(new Metric(
                "SUS Score",         50, Direction.HIGHER, 0, 100, "points", 82, 0));
        usability.addMetric(new Metric(
                "Task Success Rate", 50, Direction.HIGHER, 0, 100, "%",      91, 0));

        // ---------- Security (coefficient: 25) ----------
        Dimension security = new Dimension("Security", 25, 0.0);
        security.addMetric(new Metric(
                "Auth Failure Rate",       50, Direction.LOWER,  0, 100, "%",    2,  0));
        security.addMetric(new Metric(
                "Data Encryption Coverage",50, Direction.HIGHER, 0, 100, "%",   97,  0));

        // ---------- Performance Efficiency (coefficient: 20) ----------
        Dimension performance = new Dimension("Performance Efficiency", 20, 0.0);
        performance.addMetric(new Metric(
                "API Response Time", 50, Direction.LOWER,  0, 2000, "ms",    320, 0));
        performance.addMetric(new Metric(
                "Throughput",        50, Direction.HIGHER, 0, 1000, "req/s", 650, 0));

        // ---------- Reliability (coefficient: 25) ----------
        Dimension reliability = new Dimension("Reliability", 25, 0.0);
        reliability.addMetric(new Metric(
                "System Uptime", 50, Direction.HIGHER, 95, 100, "%",   99.5, 0));
        reliability.addMetric(new Metric(
                "MTTR",          50, Direction.LOWER,   0, 240, "min",   30, 0));

        Scenario scenario = new Scenario(
                "Scenario A - Team Alpha",
                Mode.HEALTH,
                QualityType.Product,
                new ArrayList<>()
        );
        scenario.addDimension(usability);
        scenario.addDimension(security);
        scenario.addDimension(performance);
        scenario.addDimension(reliability);
        return scenario;
    }

    // ------------------------------------------------------------------ //
    //  HEALTH — Scenario B  (Process Quality)
    // ------------------------------------------------------------------ //
    private static Scenario buildHealthScenarioB() {

        // ---------- Sprint Efficiency (coefficient: 25) ----------
        Dimension sprintEfficiency = new Dimension("Sprint Efficiency", 25, 0.0);
        sprintEfficiency.addMetric(new Metric(
                "Sprint Velocity",        50, Direction.HIGHER, 0, 100, "points", 65, 0));
        sprintEfficiency.addMetric(new Metric(
                "Sprint Completion Rate", 50, Direction.HIGHER, 0, 100, "%",      74, 0));

        // ---------- Code Quality (coefficient: 25) ----------
        Dimension codeQuality = new Dimension("Code Quality", 25, 0.0);
        codeQuality.addMetric(new Metric(
                "Code Coverage", 50, Direction.HIGHER, 0, 100, "%",        72, 0));
        codeQuality.addMetric(new Metric(
                "Bug Density",   50, Direction.LOWER,  0,  20, "bugs/KLOC", 6, 0));

        // ---------- Team Collaboration (coefficient: 25) ----------
        Dimension teamCollab = new Dimension("Team Collaboration", 25, 0.0);
        teamCollab.addMetric(new Metric(
                "Code Review Rate",    50, Direction.HIGHER, 0, 100, "%", 80, 0));
        teamCollab.addMetric(new Metric(
                "Documentation Score", 50, Direction.HIGHER, 0, 100, "%", 65, 0));

        // ---------- Compliance & Audit (coefficient: 25) ----------
        Dimension compliance = new Dimension("Compliance & Audit", 25, 0.0);
        compliance.addMetric(new Metric(
                "HIPAA Compliance Score", 50, Direction.HIGHER, 0, 100, "%",  88, 0));
        compliance.addMetric(new Metric(
                "Audit Finding Count",    50, Direction.LOWER,  0,  50, "findings", 8, 0));

        Scenario scenario = new Scenario(
                "Scenario B - Team Beta",
                Mode.HEALTH,
                QualityType.Process,
                new ArrayList<>()
        );
        scenario.addDimension(sprintEfficiency);
        scenario.addDimension(codeQuality);
        scenario.addDimension(teamCollab);
        scenario.addDimension(compliance);
        return scenario;
    }
}