package com.balauria.bts_website;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ✅ Old system fields (KEEP)
    private String email;

    @Column(length = 1000)
    private String message;

    // ✅ New system fields (ADDED)
    private String phone;
    private String location;

    @Column(length = 1000)
    private String requirement;

    // ✅ Timestamp (NEW - very useful)
    private LocalDateTime createdAt;

    // 👉 Default constructor (important)
    public Lead() {
        this.createdAt = LocalDateTime.now(); // auto-set time
    }

    // 👉 Parameterized constructor (old)
    public Lead(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    // 👉 Optional new constructor (advanced use)
    public Lead(String name, String phone, String location, String requirement) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.requirement = requirement;
        this.createdAt = LocalDateTime.now();
    }

    // 👉 Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}