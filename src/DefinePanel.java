import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefinePanel extends StepPanel {

    private ButtonGroup qualityTypeGroup;
    private JRadioButton productBtn;
    private JRadioButton processBtn;

    private ButtonGroup modeGroup;
    private JRadioButton healthBtn;
    private JRadioButton educationBtn;

    private ButtonGroup scenarioGroup;
    private JPanel scenarioPanel;

    public DefinePanel(MainFrame mainFrame) {
        super(mainFrame);
        buildUI();
    }

    private void buildUI() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.WHITE);
        main.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Quality Type
        main.add(new JLabel("Quality Type:"));
        main.add(Box.createVerticalStrut(6));
        qualityTypeGroup = new ButtonGroup();
        productBtn  = new JRadioButton("Product Quality");
        processBtn  = new JRadioButton("Process Quality");
        qualityTypeGroup.add(productBtn);
        qualityTypeGroup.add(processBtn);
        productBtn.setSelected(true);
        productBtn.setBackground(Color.WHITE);
        processBtn.setBackground(Color.WHITE);
        main.add(productBtn);
        main.add(processBtn);

        main.add(Box.createVerticalStrut(16));

        // Mode
        main.add(new JLabel("Mode:"));
        main.add(Box.createVerticalStrut(6));
        modeGroup = new ButtonGroup();
        healthBtn    = new JRadioButton("Health");
        educationBtn = new JRadioButton("Education");
        modeGroup.add(healthBtn);
        modeGroup.add(educationBtn);
        educationBtn.setSelected(true);
        healthBtn.setBackground(Color.WHITE);
        educationBtn.setBackground(Color.WHITE);
        healthBtn.addActionListener(e -> refreshScenarios());
        educationBtn.addActionListener(e -> refreshScenarios());
        main.add(healthBtn);
        main.add(educationBtn);

        main.add(Box.createVerticalStrut(16));

        // Scenario
        main.add(new JLabel("Scenario:"));
        main.add(Box.createVerticalStrut(6));
        scenarioPanel = new JPanel();
        scenarioPanel.setLayout(new BoxLayout(scenarioPanel, BoxLayout.Y_AXIS));
        scenarioPanel.setBackground(Color.WHITE);
        scenarioGroup = new ButtonGroup();
        main.add(scenarioPanel);

        refreshScenarios();

        add(main, BorderLayout.CENTER);
        add(buildNavBar(true, "Next"), BorderLayout.SOUTH);
    }

    private void refreshScenarios() {
        scenarioPanel.removeAll();
        scenarioGroup = new ButtonGroup();

        Mode selectedMode = healthBtn.isSelected() ? Mode.HEALTH : Mode.EDUCATION;
        List<String> names = ScenarioRepository.getScenariosForMode(selectedMode);

        for (String name : names) {
            JRadioButton rb = new JRadioButton(name);
            rb.setBackground(Color.WHITE);
            scenarioGroup.add(rb);
            scenarioPanel.add(rb);
        }

        if (!names.isEmpty()) {
            scenarioGroup.getElements().nextElement().setSelected(true);
        }

        scenarioPanel.revalidate();
        scenarioPanel.repaint();
    }

    private String getSelectedScenarioName() {
        java.util.Enumeration<AbstractButton> elements = scenarioGroup.getElements();
        while (elements.hasMoreElements()) {
            AbstractButton btn = elements.nextElement();
            if (btn.isSelected()) return btn.getText();
        }
        return "";
    }

    @Override
    public void onNext() {
        String scenarioName = getSelectedScenarioName();
        if (scenarioName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a scenario.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Scenario scenario = ScenarioRepository.getScenario(scenarioName);
        if (scenario == null) {
            JOptionPane.showMessageDialog(this, "Scenario not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        mainFrame.setCurrentScenario(scenario);
        mainFrame.nextStep();
    }
}