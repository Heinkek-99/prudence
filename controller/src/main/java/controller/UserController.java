package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import dao.UserDAO;
import model.User;

/**
 * 
 */
public class UserController {
	
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

	private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Create a new user.
     *
     * @param username Username.
     * @param password Plain text password.
     * @param role User role (admin, accountant, director, secretaire).
     */
    // Créer un utilisateur
    public void createUser(String username, String password, String role) throws SQLException {
        // Hachage du mot de passe avant de l'envoyer au DAO
        User user = new User(username, password, role);
        userDAO.createUser(user);
    }
    
 // Obtenir tous les utilisateurs
   public List<User> getAllUsers() throws SQLException {
       return userDAO.getAllUsers();
   }

   // Obtenir un utilisateur par ID
//    public User getUserById(int id) throws SQLException {
//        return userDAO.getUserById(id);
//    }

   // Obtenir un utilisateur par role
   public User getUserByRolUser(String role) throws SQLException {
       return userDAO.getUserByRolUser(role);
   }

    /**
     * Authenticate a user by username and password.
     *
     * @param username Username.
     * @param password Plain text password.
     * @return true if authentication succeeds, otherwise false.
     */
    

    
    public User login(String username, String password) {
        User user = userDAO.authenticate(username, password);
        if (user != null ) {
        	LOGGER.info("Connexion réussie pour : " + username);
        } else {
            LOGGER.warning("Échec de connexion pour : " + username);
        }

        return user; 
    }

}
