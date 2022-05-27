package com.example.car_assistant.mail_sender;


public interface EmailService {
    void sendMail(String to, String subject, String text);
}
