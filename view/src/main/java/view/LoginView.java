package view;

import javax.swing.*;

import controller.UserController;
import dao.UserDAO;
import model.User;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginView extends JFrame {
    
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private UserController userController;

    public LoginView(UserController userController) {
        this.userController = userController; 

        setTitle("Connexion");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); 

        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        usernameLabel.setBounds(50, 30, 120, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 30, 150, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(50, 70, 120, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 70, 150, 25);
        panel.add(passwordField);

        loginButton = new JButton("Connexion");
        loginButton.setBounds(150, 110, 100, 30);
        panel.add(loginButton);

        loginButton.addActionListener(new LoginAction());

        add(panel);
    }

    private class LoginAction implements ActionListener {
       
    	@Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = userController.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "Connexion réussie !");
            	dispose(); // Fermer l'interface de connexion
                switch (user.getRole().toLowerCase()) {
                    case "admin":
                        new AdminDashboardView(userController).setVisible(true);
                        break;
                    case "director":
                        new DirectorDashboardView().setVisible(true);
                        break;
                    case "accountant":
                        new AccountantDashboardView().setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Role inconnu. Aucun tableau de bord disponible pour ce rôle.");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Identifiants incorrects !");
            }
        }
    }

}

