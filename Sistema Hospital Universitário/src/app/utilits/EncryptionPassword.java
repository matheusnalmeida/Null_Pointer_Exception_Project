package app.utilits;

import java.security.MessageDigest;

public class EncryptionPassword {

    public static String encrypt(String password) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            String encryptedPassword = hexString.toString();
            return encryptedPassword;
        } catch (Exception exception) {
        }
        return password;
    }
}
