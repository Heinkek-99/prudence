package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.UserController;
import dao.UserDAO;
import utils.DatabaseUtils;
import view.*;

public class Main {
    
//	public static void main( String[] args ) {
//		 UserController userController = new UserController(new UserDAO(DatabaseUtils.getConnection()));
//	
//	     SwingUtilities.invokeLater(() -> {
//	         String[] options = {"LoginView", "CreateUserView"};
//	         String choice = (String) JOptionPane.showInputDialog(
//	             null,
//	             "Choisissez une vue à lancer",
//	             "Lancement de l'application",
//	             JOptionPane.QUESTION_MESSAGE,
//	             null,
//	             options,
//	             options[0]
//	            		 
//	         );
//	
//	         if ("UserView".equals(choice)) {
//	             new LoginView(userController).setVisible(true);
//	         } else if ("CreateUserView".equals(choice)) {
//	             new CreateUserView(userController).setVisible(true);
//	         }
//	     });
//    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserController userController = new UserController(new UserDAO(DatabaseUtils.getConnection()));
            new LoginView(userController).setVisible(true);
        });
    }
}
