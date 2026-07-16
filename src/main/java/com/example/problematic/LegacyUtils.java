package com.example.problematic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LegacyUtils {
    public static String decodeBase64(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Base64 value must not be null");
        }

        try {
            return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
            return reader.readLine();
        } finally {
            if (process.isAlive()) {
                process.destroy();
            }
            throw new IllegalArgumentException("Invalid Base64 input", e);
        }
    }    

