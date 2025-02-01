package org.urlshort.util;


import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashUtil {
    public static String generateHash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean validatePassword(String password, String hash){
        var newHash = BCrypt.hashpw(password, getSalt(hash));
        return newHash.equals(hash);
    }

    private static String getSalt(String hash){
        return hash.substring(0, 29);
    }
}
