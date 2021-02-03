package com.dev.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String SHA_512 = "SHA-512";

    private HashUtil() {
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_512);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte bytes : digest) {
                builder.append(String.format("%02x", bytes));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Can't create hash using SHA-512 algorithm", e);
        }
        return builder.toString();
    }
}
