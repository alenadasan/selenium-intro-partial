package utils;

import java.util.UUID;

public class StringUtils {

    public static String getRandomEmailAddress() {
        String email = UUID.randomUUID().toString().substring(0, 14) + "@mailnesia.com";
        return email;
    }
}