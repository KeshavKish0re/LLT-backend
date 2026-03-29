package com.balauria.bts_website;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*") // ✅ Allow frontend (important for localhost)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Value("${admin.username}")
    private String adminUser;

    @Value("${admin.password}")
    private String adminPass;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        // ✅ Null safety check
        if (username == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Username or password missing"));
        }

        // ✅ Debug logs (remove later if needed)
        System.out.println("CONFIG USER: " + adminUser);
        System.out.println("CONFIG PASS: " + adminPass);
        System.out.println("INPUT USER: " + username);
        System.out.println("INPUT PASS: " + password);

        // ✅ Main validation
        if (adminUser.equals(username.trim()) && adminPass.equals(password.trim())) {
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid username or password"));
    }
}