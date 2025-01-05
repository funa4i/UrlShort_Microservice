package org.urlshort.utils;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class ShortUrUtil {

    private static final StringBuilder  alphabet = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-");

    public static String getNextValue() {
        var sr = new SecureRandom();
        StringBuilder newUrl = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            newUrl.append(alphabet.charAt(sr.nextInt(alphabet.length() + 1)));
        }
        return newUrl.toString();
    }
}
