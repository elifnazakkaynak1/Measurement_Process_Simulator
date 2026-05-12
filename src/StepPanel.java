import javax.swing.*;
import java.awt.*;

public abstract class StepPanel extends JPanel {

    protected MainFrame mainFrame;

    public StepPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    public abstract void onNext();

    public void onBack() {
        mainFrame.prevStep();
    }

    public void onActivated() { }

    protected JPanel buildNavBar(boolean showBack, String nextLabel) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(240, 240, 240));
        bar.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        if (showBack) {
            JButton backBtn = new JButton("<- Back");
            backBtn.addActionListener(e -> onBack());
            bar.add(backBtn, BorderLayout.WEST);
        }

        if (nextLabel != null) {
            JButton nextBtn = new JButton(nextLabel + " ->");
            nextBtn.addActionListener(e -> onNext());
            bar.add(nextBtn, BorderLayout.EAST);
        }

        return bar;
    }
}