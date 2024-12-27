package view;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;


import controller.UserController;
import dao.UserDAO;
import utils.DatabaseUtils;

public class CreateUserView  extends JFrame {
    
	private final UserController userController;

    public CreateUserView(UserController userController) {
        this.userController = userController;

        setTitle("Créer un Utilisateur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Champs de saisie
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField();

        JLabel roleLabel = new JLabel("Rôle:");
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"admin", "accountant", "director", "secretaire"});

        // Bouton de création
        JButton createButton = new JButton("Enregistrer");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(roleLabel);
        add(roleComboBox);
        add(new JLabel()); // Espace vide
        add(createButton);

        // Action du bouton
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateUserView.this, "Veuillez remplir tous les champs !");
                } else {
                    try {
                        userController.createUser(username, password, role);
                        JOptionPane.showMessageDialog(null, "Utilisateur créé avec succès !");
                        dispose(); // Ferme la fenêtre après la création
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            
            }
        });
    }
}
