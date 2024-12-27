package view;

import javax.swing.*;

public class AccountantDashboardView extends JFrame {
    public AccountantDashboardView() {
        setTitle("Dashboard Comptable");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bienvenue sur le tableau de bord comptable.");
        panel.add(label);

        add(panel);
    }
}
