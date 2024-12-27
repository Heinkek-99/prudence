package dao;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;

import model.User;

public class UserDAO {
	
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    public User authenticate(String username, String plainPassword) {
        String query = "SELECT * FROM Users WHERE Username = ? AND Password = ? AND archived = FALSE";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, plainPassword);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String role = rs.getString("Role"); // Récupérer le rôle
                LOGGER.info("Utilisateur trouvé : " + username + plainPassword);
                LOGGER.info("Mot de passe vérifié avec succès.");
                LOGGER.info("User authenticated successfully: " + username);
                return new User(
                        rs.getInt("ID_User"),
                        rs.getString("Username"),
                        rs.getString("Password"),  // Haché
                        role,
                        rs.getBoolean("archived")
                );
            } else {
                LOGGER.warning("Échec de la vérification du mot de passe: " + username);
            }
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de l'exécution de la requête SQL : " + e.getMessage());
            throw new RuntimeException("Database query error", e);
        }
        return null;
    }
    
    /**
     * Find a user by username.
     *
     * @param username Username to search for.
     * @return Optional of User.
     */
    
    public User findByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE Username = ? AND archived = FALSE";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    String role = rs.getString("Role"); // Récupérer le rôle
                    boolean archived = rs.getBoolean("archived");
                    LOGGER.info("Utilisateur trouvé : " + username);

                User user = new User(
                        rs.getInt("ID_User"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        role,
                        archived
                );
                return user;
            }
        }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    
     //
    
    /**
     * Save a new user with hashed password.
     *
     * @param user User object to save.
     */
    
    // Créer un nouvel utilisateur
    public void createUser(User user) {
        String sql = "INSERT INTO Users (Username, Password, Role, archived) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // Hachage ici
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.isArchived());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Erreur lors de la création de l'utilisateur : " + e.getMessage());
        }
    }

    // Récupérer un utilisateur par ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM Users WHERE ID_User = ? AND archived = FALSE";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("ID_User"),
                            rs.getString("Username"),
                            rs.getString("Password"),
                            rs.getString("Role"),
                            rs.getBoolean("archived")
                    );
                }
            }
        }catch (SQLException e) {
            LOGGER.severe("Erreur lors de la récupération de l'utilisateur par ID : " + e.getMessage());
        }
        return null;
    }

    // Récupérer tous les utilisateurs non archivés
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE archived = FALSE";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("ID_User"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getBoolean("archived")

                ));
            }
        }catch (SQLException e) {
            LOGGER.severe("Erreur lors de la récupération de tous les utilisateurs : " + e.getMessage());
        }
        return users;
    }
    
    public void updateUser(User user) {
        String sql = "UPDATE Users SET Username = ?, Password = ?, Role = ?, archived = ? WHERE ID_User = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // Toujours hacher avant
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.isArchived());
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            LOGGER.severe("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }
    }
}
