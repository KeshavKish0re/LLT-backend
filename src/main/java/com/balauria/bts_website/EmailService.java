package com.balauria.bts_website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // ✅ 1. Mail to YOU (admin) - FULL DATA
    public void sendAdminEmail(String name, String email, String phone, String location, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("yourgmail@gmail.com"); // 🔥 Replace with your real email
        mail.setSubject("🚀 New Lead from Website");

        mail.setText(
                "🔥 New Lead Received:\n\n" +

                "👤 Name: " + name + "\n" +
                "📧 Email: " + email + "\n" +
                "📞 Phone: " + phone + "\n" +
                "📍 Location: " + location + "\n\n" +

                "📝 Requirement:\n" + message + "\n\n" +

                "-----------------------------\n" +
                "Sent from LocalLift Website"
        );

        mailSender.send(mail);
    }

    // ✅ 2. Auto-reply to USER
    public void sendAutoReply(String userEmail, String userName) {

        // 🛑 Avoid sending to fake emails
        if (userEmail == null || userEmail.isEmpty() || userEmail.contains("@noemail.com")) {
            return;
        }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(userEmail);
        mail.setSubject("✅ We received your message - LocalLift Technologies");

        mail.setText(
                "Hi " + userName + ",\n\n" +
                "Thank you for contacting LocalLift Technologies 🚀\n\n" +
                "We have received your request and our team will contact you shortly.\n\n" +
                "Best regards,\n" +
                "LocalLift Technologies Team"
        );

        mailSender.send(mail);
    }
}