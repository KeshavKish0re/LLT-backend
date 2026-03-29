package com.balauria.bts_website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
    public ResponseEntity<String> saveLead(@RequestBody Lead lead) {
        try {

            // 🧠 SMART NORMALIZATION

            // If message missing → use requirement
            if ((lead.getMessage() == null || lead.getMessage().isEmpty())
                    && lead.getRequirement() != null && !lead.getRequirement().isEmpty()) {
                lead.setMessage(lead.getRequirement());
            }

            // If email missing → create fallback using phone
            if ((lead.getEmail() == null || lead.getEmail().isEmpty())
                    && lead.getPhone() != null && !lead.getPhone().isEmpty()) {
                lead.setEmail(lead.getPhone() + "@noemail.com");
            }

            // 🛡️ FINAL VALIDATION
            if (lead.getName() == null || lead.getName().isEmpty()
                    || lead.getMessage() == null || lead.getMessage().isEmpty()) {

                return ResponseEntity.badRequest().body("⚠ Required fields missing");
            }

            // 🧾 DEBUG LOG (FULL DATA)
            System.out.println("🚀 New Lead Received:");
            System.out.println("Name: " + lead.getName());
            System.out.println("Email: " + lead.getEmail());
            System.out.println("Phone: " + lead.getPhone());
            System.out.println("Location: " + lead.getLocation());
            System.out.println("Message: " + lead.getMessage());

            // 💾 SAVE TO DATABASE
            leadRepository.save(lead);

            // 📧 SEND ADMIN EMAIL (FULL DATA)
            emailService.sendAdminEmail(
                    lead.getName(),
                    lead.getEmail(),
                    lead.getPhone(),
                    lead.getLocation(),
                    lead.getMessage()
            );

            // 📩 AUTO-REPLY (only if real email)
            if (lead.getEmail() != null && !lead.getEmail().contains("@noemail.com")) {
                emailService.sendAutoReply(
                        lead.getEmail(),
                        lead.getName()
                );
            }

            return ResponseEntity.ok("Message Sent Successfully 🚀");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error sending message ❌");
        }
    }
}