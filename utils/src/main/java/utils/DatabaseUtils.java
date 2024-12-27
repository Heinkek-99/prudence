package utils;

import java.sql.*;

public class DatabaseUtils {
	private static DatabaseUtils instance;
    private Connection connection;
	
	private static final String URL = "jdbc:mysql://localhost:3306/prudence_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";


//    private DatabaseUtils() {
//    	try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("MySQL JDBC Driver not found.", e);
//        }
//    }

    public static DatabaseUtils getInstance() {
        if (instance == null) {
            instance = new DatabaseUtils();
        }
        return instance;
    }

    public static Connection getConnection(){
//        return DriverManager.getConnection(URL, USER, PASSWORD);
    	try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
