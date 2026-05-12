import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CollectPanel extends StepPanel {

    private JPanel contentPanel;
    private Analyzer analyzer = new Analyzer();

    public CollectPanel(MainFrame mainFrame) {
        super(mainFrame);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
        add(buildNavBar(true, "Analyse"), BorderLayout.SOUTH);
    }

    @Override
    public void onActivated() {
        contentPanel.removeAll();
        Scenario scenario = mainFrame.getCurrentScenario();
        if (scenario == null) return;

        for (Dimension dim : scenario.getDimensions()) {
            for (Metric m : dim.getMetrics()) analyzer.calculateMetricScore(m);
            analyzer.calculateDimensionScore(dim);
        }

        for (Dimension dim : scenario.getDimensions()) {
            JLabel dimLabel = new JLabel(dim.getName() + "  (Coefficient: " + dim.getCoefficient() + ")");
            dimLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            contentPanel.add(dimLabel);
            contentPanel.add(Box.createVerticalStrut(4));

            String[] cols = {"Metric", "Direction", "Range", "Value", "Score (1-5)", "Coeff / Unit"};
            DefaultTableModel model = new DefaultTableModel(cols, 0) {
                public boolean isCellEditable(int r, int c) { return false; }
            };
            for (Metric m : dim.getMetrics()) {
                String range     = (int)m.getMinValue() + " - " + (int)m.getMaxValue();
                String dir       = m.getDirection() == Direction.HIGHER ? "Higher" : "Lower";
                String coeffUnit = m.getCoefficient() + " / " + m.getUnit();
                model.addRow(new Object[]{m.getName(), dir, range, m.getValue(), m.getScore(), coeffUnit});
            }

            JTable table = new JTable(model);
            table.setRowHeight(24);
            JScrollPane tp = new JScrollPane(table);
            tp.setPreferredSize(new java.awt.Dimension(700, table.getRowHeight() * (model.getRowCount() + 1) + 4));
            contentPanel.add(tp);
            contentPanel.add(Box.createVerticalStrut(14));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override
    public void onNext() { mainFrame.nextStep(); }
}