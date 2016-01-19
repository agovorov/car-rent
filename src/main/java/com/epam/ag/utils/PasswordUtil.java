package com.epam.ag.utils;

/**
 * @author Govorov Andrey
 */
public class PasswordUtil {

    private String algorithm = "MD5";

    /**
     * Generating random password
     *
     * @return
     */
    public static String generateRandomPassword() {
        // TODO Generate random password
        String password = "password";
        return password;
    }

    /**
     * Generate random password and hash them
     *
     * @return
     */
    public static String generateHashedRandomPassword() {
        String plainPassword = generateRandomPassword();
        return hashPassword(plainPassword);
    }

    /**
     * Hash plain password with custom algorithm
     *
     * @param plainPassword
     * @return
     */
    public static String hashPassword(String plainPassword) {
        // TODO Crypt password using method from `algorithm`
        return plainPassword;
    }

    /**
     * Validate two passwords
     *
     * @param plainPassword
     * @param storedPassword
     * @return
     */
    public static boolean validatePassword(String plainPassword, String storedPassword) {
        String hashedPassword = hashPassword(plainPassword);
        return hashedPassword.equals(storedPassword);
    }
}
