package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

import controller.UserController;

public class AdminDashboardView extends JFrame {
    private final UserController userController; // Assurez-vous de définir userController

    public AdminDashboardView(UserController userController) {
        this.userController = userController;
        
        setTitle("School Management System - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

     // Ajouter une barre de menu
        setJMenuBar(createMenuBar());

        // Ajouter le tableau de bord principal
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Accueil", createHomePanel());
        tabbedPane.addTab("Gestion des utilisateurs", createUserManagementPanel());
        tabbedPane.addTab("Gestion des étudiants", createStudentManagementPanel());
        tabbedPane.addTab("Gestion des enseignants", createTeacherManagementPanel());
        tabbedPane.addTab("Paiements", createPaymentManagementPanel());
        tabbedPane.addTab("Logs", createLogManagementPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem exitItem = new JMenuItem("Quitter");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Aide");
        JMenuItem aboutItem = new JMenuItem("À propos");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "School Management System v1.0\nDéveloppé par [Votre Nom]", 
            "À propos", JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenue dans le système de gestion scolaire", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Gestion des utilisateurs");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JButton createUserButton = new JButton("Créer un Utilisateur");
        createUserButton.addActionListener(e -> {
            CreateUserView createUserView = new CreateUserView(userController);
            createUserView.setVisible(true);
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(createUserButton, BorderLayout.SOUTH);
        panel.add(new JLabel("Interface pour gérer les utilisateurs..."), BorderLayout.CENTER);
        return panel;
//        panel.add(label, BorderLayout.NORTH);
//        panel.add(new UserManagementFrame().createUserForm(), BorderLayout.CENTER);
//        return panel;
    }

    private JPanel createStudentManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Gestion des étudiants");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JLabel("Interface pour gérer les étudiants..."), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createTeacherManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Gestion des enseignants");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JLabel("Interface pour gérer les enseignants..."), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPaymentManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Gestion des paiements");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JLabel("Interface pour gérer les paiements..."), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLogManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Logs des actions");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JLabel("Interface pour afficher les logs..."), BorderLayout.CENTER);
        return panel;
    }
}
