package com.sra.studentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender mailSender;

    public void sendEnrollmentNotification(String to, String courseName, String studentName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Course Enrollment Confirmation");
        message.setText("Dear " + studentName + ",\n\n" +
                        "You have successfully enrolled in the course: " + courseName + ".\n\n" +
                        "Thank you for choosing our platform!\n\n" +
                        "Best regards,\nMuhammad Mubaasim T A");

        mailSender.send(message);
    }
}
