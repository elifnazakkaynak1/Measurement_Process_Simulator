import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnalysePanel extends StepPanel {

    private JPanel contentPanel;

    public AnalysePanel(MainFrame mainFrame) {
        super(mainFrame);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
        add(buildNavBar(true, null), BorderLayout.SOUTH);
    }

    @Override
    public void onActivated() {
        contentPanel.removeAll();
        Scenario scenario = mainFrame.getCurrentScenario();
        if (scenario == null) return;

        List<Dimension> dims = scenario.getDimensions();
        Analyzer analyzer = new Analyzer();

        JLabel sec1 = new JLabel("Dimension Scores:");
        sec1.setFont(new Font("SansSerif", Font.BOLD, 13));
        contentPanel.add(sec1);
        contentPanel.add(Box.createVerticalStrut(8));

        for (Dimension dim : dims) {
            JPanel row = new JPanel(new BorderLayout(8, 0));
            row.setBackground(Color.WHITE);
            row.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 28));

            JLabel nameLbl = new JLabel(dim.getName());
            nameLbl.setPreferredSize(new java.awt.Dimension(200, 24));

            JProgressBar bar = new JProgressBar(0, 100);
            bar.setValue((int)(dim.getScore() / 5.0 * 100));
            bar.setStringPainted(true);
            bar.setString(String.format("%.2f / 5.00", dim.getScore()));

            row.add(nameLbl, BorderLayout.WEST);
            row.add(bar, BorderLayout.CENTER);
            contentPanel.add(row);
            contentPanel.add(Box.createVerticalStrut(6));
        }

        contentPanel.add(Box.createVerticalStrut(20));

        // chart
        JLabel sec2 = new JLabel("Radar Chart:");
        sec2.setFont(new Font("SansSerif", Font.BOLD, 13));
        contentPanel.add(sec2);
        contentPanel.add(Box.createVerticalStrut(8));

        RadarChart chart = new RadarChart(dims);
        chart.setPreferredSize(new java.awt.Dimension(400, 300));
        chart.setMaximumSize(new java.awt.Dimension(500, 300));
        chart.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(chart);
        contentPanel.add(Box.createVerticalStrut(20));

        // gap analysis
        JLabel sec3 = new JLabel("Gap Analysis:");
        sec3.setFont(new Font("SansSerif", Font.BOLD, 13));
        contentPanel.add(sec3);
        contentPanel.add(Box.createVerticalStrut(8));

        Dimension lowest = analyzer.findLowestDimension(dims);
        if (lowest != null) {
            double gap   = analyzer.gapAnalysis(lowest);
            String label = analyzer.qualityLabel(lowest.getScore());

            contentPanel.add(new JLabel("Lowest Dimension: " + lowest.getName()));
            contentPanel.add(new JLabel(String.format("Score: %.2f / 5.00", lowest.getScore())));
            contentPanel.add(new JLabel(String.format("Gap: %.2f", gap)));
            contentPanel.add(new JLabel("Quality Level: " + label));
            contentPanel.add(Box.createVerticalStrut(6));
            contentPanel.add(new JLabel("This dimension has the lowest score and requires the most improvement."));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override
    public void onNext() { }

    private static class RadarChart extends JPanel {
        private final List<Dimension> dims;

        RadarChart(List<Dimension> dims) {
            this.dims = dims;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (dims == null || dims.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;
            int radius = Math.min(cx, cy) - 50;
            int n = dims.size();

            for (int level = 1; level <= 5; level++) {
                double r = radius * level / 5.0;
                int[] xs = new int[n], ys = new int[n];
                for (int i = 0; i < n; i++) {
                    double angle = 2 * Math.PI * i / n - Math.PI / 2;
                    xs[i] = (int)(cx + r * Math.cos(angle));
                    ys[i] = (int)(cy + r * Math.sin(angle));
                }
                g2.setColor(new Color(200, 200, 200));
                g2.drawPolygon(xs, ys, n);
            }

            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n - Math.PI / 2;
                g2.setColor(new Color(180, 180, 180));
                g2.drawLine(cx, cy,
                        (int)(cx + radius * Math.cos(angle)),
                        (int)(cy + radius * Math.sin(angle)));
            }

            int[] dx = new int[n], dy = new int[n];
            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n - Math.PI / 2;
                double r = radius * dims.get(i).getScore() / 5.0;
                dx[i] = (int)(cx + r * Math.cos(angle));
                dy[i] = (int)(cy + r * Math.sin(angle));
            }
            g2.setColor(new Color(100, 150, 255, 80));
            g2.fillPolygon(dx, dy, n);
            g2.setColor(new Color(60, 100, 220));
            g2.setStroke(new BasicStroke(2));
            g2.drawPolygon(dx, dy, n);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n - Math.PI / 2;
                int lx = (int)(cx + (radius + 30) * Math.cos(angle));
                int ly = (int)(cy + (radius + 30) * Math.sin(angle));
                String name = dims.get(i).getName();
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(name, lx - fm.stringWidth(name) / 2, ly + fm.getAscent() / 2);
            }

            g2.dispose();
        }
    }
}