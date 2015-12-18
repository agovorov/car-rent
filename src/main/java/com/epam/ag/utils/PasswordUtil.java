package com.epam.ag.utils;

/**
 * @author Govorov Andrey
 */
public class PasswordUtil {

    public static String generateRandomPassword() {
        // Generating random password
        String password = "password";
        return hashPassword(password);
    }

    public static String hashPassword(String plainPassword) {
        return plainPassword;
    }

    public static boolean validatePassword(String plainPassword, String storedPassword) {
        String hashedPassword = hashPassword(plainPassword);
        return hashedPassword == storedPassword;
    }
}
