package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
//	public static String hashPassword(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                hexString.append(String.format("%02x", b));
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Erreur lors du hachage du mot de passe", e);
//        }
//    }
	
	public static String hashPassword(String plainPassword) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainPassword, salt);
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
