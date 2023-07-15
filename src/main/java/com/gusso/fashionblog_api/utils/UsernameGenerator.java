package com.gusso.fashionblog_api.utils;

import java.util.Random;

public class UsernameGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

//    public static void main(String[] args) {
//        int length = 6;
//        String randomString = generateRandomString(length);
//        System.out.println("Random String: " + randomString);
//    }
}

