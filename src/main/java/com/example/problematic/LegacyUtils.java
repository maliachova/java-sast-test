package com.example.problematic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LegacyUtils {
    public static String decodeBase64(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }

    public static String readSystemInfo() throws Exception {
        Process process = Runtime.getRuntime().exec("cmd /c ver");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.readLine();
        }
    }
}
