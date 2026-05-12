import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends StepPanel {

    private JTextField usernameField;
    private JTextField schoolField;
    private JTextField sessionField;

    public ProfilePanel(MainFrame mainFrame) {
        super(mainFrame);
        buildUI();
    }

    private void buildUI() {
        setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        usernameField = new JTextField(20);
        schoolField   = new JTextField(20);
        sessionField  = new JTextField(20);

        String[] labels = {"Username:", "School:", "Session Name:"};
        JTextField[] fields = {usernameField, schoolField, sessionField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i * 2;
            form.add(new JLabel(labels[i]), gbc);
            gbc.gridy = i * 2 + 1;
            form.add(fields[i], gbc);
        }

        add(form, BorderLayout.CENTER);
        add(buildNavBar(false, "Next"), BorderLayout.SOUTH);
    }

    @Override
    public void onNext() {
        String username = usernameField.getText().trim();
        String school   = schoolField.getText().trim();
        String session  = sessionField.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username to continue.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (school.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your school name to continue.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (session.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a session name to continue.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        mainFrame.setCurrentUser(new User(username, school, session));
        mainFrame.nextStep();
    }
}