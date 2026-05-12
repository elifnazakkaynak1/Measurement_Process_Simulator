import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private int currentStep = 0;
    private List<StepPanel> panels = new ArrayList<>();
    private JPanel stepIndicatorPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private static final String[] STEP_NAMES = {"Profile", "Define", "Plan", "Collect", "Analyse"};

    private User currentUser;
    private Scenario currentScenario;

    public MainFrame() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        buildStepIndicator();
        buildCardPanel();
        setVisible(true);
    }

    private void buildStepIndicator() {
        stepIndicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        stepIndicatorPanel.setBackground(new Color(230, 230, 230));
        stepIndicatorPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        refreshStepIndicator();
        add(stepIndicatorPanel, BorderLayout.NORTH);
    }

    public void refreshStepIndicator() {
        stepIndicatorPanel.removeAll();

        for (int i = 0; i < STEP_NAMES.length; i++) {
            String text;
            if (i < currentStep) {
                text = "✓ " + STEP_NAMES[i];
            } else {
                text = (i + 1) + ". " + STEP_NAMES[i];
            }

            JLabel lbl = new JLabel(text);
            lbl.setFont(new Font("SansSerif", i == currentStep ? Font.BOLD : Font.PLAIN, 13));
            lbl.setForeground(i == currentStep ? new Color(0, 80, 200)
                    : i < currentStep ? new Color(0, 150, 0)
                    : Color.GRAY);
            stepIndicatorPanel.add(lbl);

            if (i < STEP_NAMES.length - 1) {
                JLabel arrow = new JLabel(">");
                arrow.setForeground(Color.GRAY);
                stepIndicatorPanel.add(arrow);
            }
        }

        stepIndicatorPanel.revalidate();
        stepIndicatorPanel.repaint();
    }

    private void buildCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        ProfilePanel  p0 = new ProfilePanel(this);
        DefinePanel   p1 = new DefinePanel(this);
        PlanPanel     p2 = new PlanPanel(this);
        CollectPanel  p3 = new CollectPanel(this);
        AnalysePanel  p4 = new AnalysePanel(this);

        panels.add(p0); panels.add(p1); panels.add(p2); panels.add(p3); panels.add(p4);
        cardPanel.add(p0, "0"); cardPanel.add(p1, "1"); cardPanel.add(p2, "2");
        cardPanel.add(p3, "3"); cardPanel.add(p4, "4");

        add(cardPanel, BorderLayout.CENTER);
    }

    public void showStep(int step) {
        currentStep = step;
        cardLayout.show(cardPanel, String.valueOf(step));
        refreshStepIndicator();
    }

    public void nextStep() {
        if (currentStep < panels.size() - 1) {
            panels.get(currentStep + 1).onActivated();
            showStep(currentStep + 1);
        }
    }

    public void prevStep() {
        if (currentStep > 0) showStep(currentStep - 1);
    }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User user) { this.currentUser = user; }
    public Scenario getCurrentScenario() { return currentScenario; }
    public void setCurrentScenario(Scenario scenario) { this.currentScenario = scenario; }
}