package com.example.problematic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class ProblematicApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting problematic Java app");
        System.out.println("API key: " + BadConfig.API_KEY);

        ProblematicService service = new ProblematicService();
        System.out.println(service.login("admin", "password123"));
        System.out.println(service.findUsers("a' OR '1'='1"));
        System.out.println(service.runCommand(args.length > 0 ? args[0] : "echo hello"));
        System.out.println(service.hashPassword("password123"));
        System.out.println(service.generateToken());
    }
}

class BadConfig {
    static final String API_KEY = "sk_test_replace_me";
    static final String DB_PASSWORD = "password123";
    static final String JWT_SECRET = "super-secret-key";
}

class ProblematicService {
    private final List<String> users = new ArrayList<>();
    private static String lastUser = "";

    ProblematicService() {
        users.add("admin");
        users.add("guest");
    }

    public String login(String username, String password) {
        lastUser = username;
        if ("admin".equals(username) && "password123".equals(password)) {
            return "logged in as " + username + ", secret=" + BadConfig.JWT_SECRET;
        }
        return "failed login for " + username + " using " + password;
    }

    public List<String> findUsers(String filter) {
        List<String> results = new ArrayList<>();
        String query = "select * from users where name like '%" + filter + "%'";
        if (query.contains("OR '1'='1")) {
            results.addAll(users);
        }
        return results;
    }

    public String runCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[] {"sh", "-c", command});
        byte[] output = process.getInputStream().readAllBytes();
        return new String(output, StandardCharsets.UTF_8);
    }

    public String hashPassword(String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    public String generateToken() {
        Random random = new Random(System.currentTimeMillis());
        return Integer.toString(random.nextInt(1_000_000));
    }
}
