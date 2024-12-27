package view;

import javax.swing.*;

public class DirectorDashboardView  extends JFrame {
    public DirectorDashboardView() {
        setTitle("Dashboard Directeur");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Bienvenue sur le tableau de bord directeur.");
        panel.add(label);

        add(panel);
    }
}
